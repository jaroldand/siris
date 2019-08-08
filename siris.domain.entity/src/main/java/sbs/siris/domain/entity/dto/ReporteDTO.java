package sbs.siris.domain.entity.dto;

import java.io.Serializable;
import java.util.List;

import sbs.siris.domain.entity.Canales;
import sbs.siris.domain.entity.Impacto;
import sbs.siris.domain.entity.Informe;
import sbs.siris.domain.entity.PlanAccion;
import sbs.siris.domain.entity.Validacion;

public class ReporteDTO implements Serializable {

	private static final long serialVersionUID = 3198480060204443378L;
	
	private String tipEvento;
	private List<Informe> informe;
	private List<Impacto> impacto;
	private List<Canales> canales;
	private List<PlanAccion> planAccion;
	private List<Validacion> validaciones;
	
	
	public List<Informe> getInforme() {
		return informe;
	}
	public void setInforme(List<Informe> informe) {
		this.informe = informe;
	}
	public List<Impacto> getImpacto() {
		return impacto;
	}
	public void setImpacto(List<Impacto> impacto) {
		this.impacto = impacto;
	}
	public List<Canales> getCanales() {
		return canales;
	}
	public void setCanales(List<Canales> canales) {
		this.canales = canales;
	}
	public List<PlanAccion> getPlanAccion() {
		return planAccion;
	}
	public void setPlanAccion(List<PlanAccion> planAccion) {
		this.planAccion = planAccion;
	}
	public List<Validacion> getValidaciones() {
		return validaciones;
	}
	public void setValidaciones(List<Validacion> validaciones) {
		this.validaciones = validaciones;
	}
	public String getTipEvento() {
		return tipEvento;
	}
	public void setTipEvento(String tipEvento) {
		this.tipEvento = tipEvento;
	}
}
