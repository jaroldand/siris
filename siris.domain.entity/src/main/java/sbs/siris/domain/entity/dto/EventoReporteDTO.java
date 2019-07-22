package sbs.siris.domain.entity.dto;

import java.io.Serializable;
import java.util.List;

import sbs.siris.domain.entity.Canales;
import sbs.siris.domain.entity.Impacto;
import sbs.siris.domain.entity.Informe;
import sbs.siris.domain.entity.PlanAccion;

public class EventoReporteDTO implements Serializable {

	private static final long serialVersionUID = -7191504371127933311L;

	private Integer idEvento;
	private Informe informe;
	private List<Canales> canales;
	private List<Canales> canalesActivos;
	
	private List<Impacto> impacto;
	
	private List<PlanAccion> planAccion;
	private List<PlanAccion> planAccionActivos;

	public Informe getInforme() {
		return informe;
	}

	public void setInforme(Informe informe) {
		this.informe = informe;
	}

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public List<Canales> getCanales() {
		return canales;
	}

	public void setCanales(List<Canales> canales) {
		this.canales = canales;
	}

	public List<Canales> getCanalesActivos() {
		return canalesActivos;
	}

	public void setCanalesActivos(List<Canales> canalesActivos) {
		this.canalesActivos = canalesActivos;
	}

	public List<Impacto> getImpacto() {
		return impacto;
	}

	public void setImpacto(List<Impacto> impacto) {
		this.impacto = impacto;
	}

	public List<PlanAccion> getPlanAccion() {
		return planAccion;
	}

	public void setPlanAccion(List<PlanAccion> planAccion) {
		this.planAccion = planAccion;
	}

	public List<PlanAccion> getPlanAccionActivos() {
		return planAccionActivos;
	}

	public void setPlanAccionActivos(List<PlanAccion> planAccionActivos) {
		this.planAccionActivos = planAccionActivos;
	}
}
