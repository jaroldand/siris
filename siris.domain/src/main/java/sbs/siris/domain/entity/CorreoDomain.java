package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import sbs.siris.cross.resources.ConstanteValor;
import sbs.siris.cross.resources.DiccionarioValor;
import sbs.siris.cross.resources.TipoValor;
import sbs.siris.data.CorreoMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class CorreoDomain extends BaseDomain<Correo> {
	
	@Autowired
	private CorreoMapper mapper;
	
	public CorreoDomain(CorreoMapper correoMapper) {
		super.setMapper(correoMapper);
	}
	
	public Correo buscarCorreoInforme(Integer idInforme) {
		
		Correo param = new  Correo();
		param.setIdEntidad(idInforme);
		param.setIdTabla(DiccionarioValor.SIRIS_INFORME);
		param.setTipCorreo(TipoValor.SIRIS_CORREO_TIP_CORREO_CORREO_INFORME);
		
		List<Correo> correo = buscar(param, null);
		
		return CollectionUtils.isEmpty(correo) ? new Correo() : correo.get(0);
	}
	
	public void grabarCorreoInforme(Correo correoInforme, Integer idInforme, String user) {
		
		if(  ObjectUtils.isEmpty(correoInforme.getIdCorreo())  ) {
			
			correoInforme.setIdEntidad(idInforme);
			correoInforme.setIdTabla(DiccionarioValor.SIRIS_INFORME);
			correoInforme.setTipCorreo(TipoValor.SIRIS_CORREO_TIP_CORREO_CORREO_INFORME);
			correoInforme.setIdCorreo( getSequenceLong() );
			correoInforme.setIndActivo(ConstanteValor.IND_ACTIVO);
			
			insert(correoInforme, user);
		} else {
			
			correoInforme.setIdEntidad(idInforme);
			correoInforme.setIdTabla(DiccionarioValor.SIRIS_INFORME);
			correoInforme.setTipCorreo(TipoValor.SIRIS_CORREO_TIP_CORREO_CORREO_INFORME);
			correoInforme.setIndActivo(ConstanteValor.IND_ACTIVO);
			
			updateByKey(correoInforme.getIdCorreo(), correoInforme, user);
		}
	}
}
