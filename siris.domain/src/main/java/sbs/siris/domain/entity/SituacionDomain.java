package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import sbs.siris.cross.resources.ConstanteValor;
import sbs.siris.data.SituacionMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class SituacionDomain extends BaseDomain<Situacion> {
	
	@Autowired
	private SituacionMapper mapper;
	
	public SituacionDomain(SituacionMapper situacionMapper) {
		super.setMapper(situacionMapper);
	}
	
	public void registrarSituacion(Situacion situacion, String user) {
		
		if( ObjectUtils.isEmpty( situacion.getIdSituacion() ) ) {//insert
			
			situacion.setIdSituacion(getSequenceLong());
			situacion.setIndActivo(ConstanteValor.IND_ACTIVO);
			
			insert(situacion, user);
			
		} else {//update
			
			situacion.setIndActivo(ConstanteValor.IND_ACTIVO);
			
			updateByKey(situacion.getIdSituacion(), situacion, user);
			
		}
	}
	
	public void inactivarSituacion(List<Situacion> situacionActivos, List<Situacion> situaciones, String user) {
		
		situacionActivos.forEach((entry) -> {
			
			Situacion situacionActivo = situaciones.stream().filter(item -> item.getIdSituacion().equals( entry.getIdSituacion() ) ).findAny().orElse(null);
			
			if(  ObjectUtils.isEmpty(situacionActivo) ) {//si no se encuentra en la lista se inactiva
				
				entry.setIndActivo(ConstanteValor.IND_INACTIVO);
				updateByKey(entry.getIdSituacion(), entry, user);
			}
			
		});
		
	}
}
