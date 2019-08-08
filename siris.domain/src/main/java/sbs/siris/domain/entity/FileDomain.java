package sbs.siris.domain.entity;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import sbs.cross.util.DateTime;
import sbs.siris.cross.resources.ConstanteValor;
import sbs.siris.cross.resources.DiccionarioValor;
import sbs.siris.cross.resources.TipoValor;
import sbs.siris.data.FileMapper;
import sbs.siris.domain.base.BaseDomain;
import sbs.siris.domain.entity.base.BaseParam;



@Service
public class FileDomain extends BaseDomain<File> {
	
	@Autowired
	private FileMapper mapper;
	
	@Autowired
	private EventoDomain eventoDomain;
	
	public FileDomain(FileMapper fileMapper) {
		super.setMapper(fileMapper);
	}
	
	public File buscarFileInformeComent(Integer idInforme) {
		
		File param = new File();
		param.setIdEntidad(idInforme);
		param.setIdTabla(DiccionarioValor.SIRIS_INFORME);
		param.setTipDocumento(TipoValor.SIRIS_FILE_TIP_DOCUMENTO_DOCUMENTO_ADJUNTO_DE_ACCIONES_EN_INFORME);
		
		List<File> files = buscar(param, null);
		
		return CollectionUtils.isEmpty(files) ? new File() : files.get(0);
	}
	
	public Long guardarFileAdjuntoAccionesEnInforme(File documento, MultipartFile file, Integer idInforme, String codUser) throws Exception {
		
		String nombreDocumento = "";
		
		try {
			nombreDocumento = new String(documento.getDesDocumento().getBytes(), "utf-8");
			documento.setDesDocumento(nombreDocumento);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if (ObjectUtils.isEmpty(documento.getIdFile()) || documento.getIdFile().equals(-1L)) {//insert
			
			documento.setIdFile(getSequenceLong());
			documento.setIndActivo(ConstanteValor.IND_ACTIVO);
			documento.setBinArchivo(file.getBytes());
			documento.setTipDocumento(TipoValor.SIRIS_FILE_TIP_DOCUMENTO_DOCUMENTO_ADJUNTO_DE_ACCIONES_EN_INFORME);
			documento.setIdEntidad(idInforme);
			documento.setIdTabla(DiccionarioValor.SIRIS_INFORME);
			
			insert(documento, codUser);
			
		} else {
			documento.setIndActivo(ConstanteValor.IND_ACTIVO);
			documento.setBinArchivo(file.getBytes());
			documento.setTipDocumento(TipoValor.SIRIS_FILE_TIP_DOCUMENTO_DOCUMENTO_ADJUNTO_DE_ACCIONES_EN_INFORME);
			
			updateByKey(documento.getIdFile(), documento, codUser);
		}
		
		return documento.getIdFile();
	}
	
	public Long registrarConstanciaPDFFile(String user, byte[] constanciaPDF, int idEvento, Date fechaIniInterrupcion) {
		
		File documento = new File();
		
		BaseParam<File> param = new BaseParam<File>();
		documento.setIdEntidad(idEvento);
		documento.setIdTabla(DiccionarioValor.SIRIS_EVENTO);
		documento.setAuditUserMod(user);
		param.setEntity(documento);
		mapper.inactivateFileByEntidad(param);
		
		Evento event = eventoDomain.obtenerEventoById(idEvento);
		
		documento.setDesDocumento("Informe_"+event.getNomEntVigCorto()+"_"+DateTime.stringFromDate(fechaIniInterrupcion, "ddMMyyyy"));
		documento.setDesExtension("pdf");
		documento.setTipDocumento(TipoValor.SIRIS_FILE_TIP_DOCUMENTO_DOCUMENTO_INFORME);
		documento.setNumTamanio( Long.valueOf( constanciaPDF.length ));
		documento.setIdFile(getSequenceLong());
		documento.setIndActivo(ConstanteValor.IND_ACTIVO);
		documento.setBinArchivo(constanciaPDF);
		documento.setIdEntidad(idEvento);
		documento.setIdTabla(DiccionarioValor.SIRIS_EVENTO);
		
		insert(documento, user);
		
		return documento.getIdFile();
	}
}
