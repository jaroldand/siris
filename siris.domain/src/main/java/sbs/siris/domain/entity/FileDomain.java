package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sbs.siris.cross.resources.ConstanteValor;
import sbs.siris.data.FileMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class FileDomain extends BaseDomain<File> {
	
	@Autowired
	private FileMapper mapper;
	
	public FileDomain(FileMapper fileMapper) {
		super.setMapper(fileMapper);
	}
	
	public File buscarFile(Integer idEvento) {
		
		File param = new File();
		param.setIdEvento(idEvento);
		
		List<File> files = buscar(param, null);
		
		return CollectionUtils.isEmpty(files) ? new File() : files.get(0);
	}
	
	public Long registrarConstanciaPDFFile(String user, byte[] constanciaPDF) {
		File documento = new File();
		
		documento.setDesDocumento("constancia");
		documento.setDesExtension("pdf");
		documento.setNumTamanio( Long.valueOf( constanciaPDF.length ));
		documento.setIdFile(getSequenceLong());
		documento.setIndActivo(ConstanteValor.IND_ACTIVO);
		documento.setBinArchivo(constanciaPDF);
		
		insert(documento, user);
		
		return documento.getIdFile();
	}
}
