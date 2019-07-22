package sbs.siris.domain.entity.dto;

import java.io.Serializable;
import java.util.Map;

import sbs.siris.domain.entity.Validacion;

public class DatosValidacionDTO implements Serializable {

	private static final long serialVersionUID = 638259013922478759L;
	
	private Map<String, Validacion> validaciones;
	private Integer idEvento;
	private String idEntidadVig;
	
	public Map<String, Validacion> getValidaciones() {
		return validaciones;
	}
	public void setValidaciones(Map<String, Validacion> validaciones) {
		this.validaciones = validaciones;
	}
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public String getIdEntidadVig() {
		return idEntidadVig;
	}
	public void setIdEntidadVig(String idEntidadVig) {
		this.idEntidadVig = idEntidadVig;
	}
}
