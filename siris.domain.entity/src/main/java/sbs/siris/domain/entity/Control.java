package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Control extends BaseEntity {
	private Short idColumna;

	private Integer idCoopac;

	private Short indObservado;

	private Short indCompleto;

	public Short getIdColumna() {
		return idColumna;
	}

	public void setIdColumna(Short idColumna) {
		this.idColumna = idColumna;
	}

	public Integer getIdCoopac() {
		return idCoopac;
	}

	public void setIdCoopac(Integer idCoopac) {
		this.idCoopac = idCoopac;
	}

	public Short getIndObservado() {
		return indObservado;
	}

	public void setIndObservado(Short indObservado) {
		this.indObservado = indObservado;
	}

	public Short getIndCompleto() {
		return indCompleto;
	}

	public void setIndCompleto(Short indCompleto) {
		this.indCompleto = indCompleto;
	}
}