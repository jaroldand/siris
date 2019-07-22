package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Usuario extends BaseEntity {
	private Integer idUsuario;

	private String codUsuario;

	private String tipUsuario;

	private Integer idCoopac;

	private Long idPersona;

	private Short indAutorizado;

	private Short indActivo;
	
	private String desToken;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getTipUsuario() {
		return tipUsuario;
	}

	public void setTipUsuario(String tipUsuario) {
		this.tipUsuario = tipUsuario;
	}

	public Integer getIdCoopac() {
		return idCoopac;
	}

	public void setIdCoopac(Integer idCoopac) {
		this.idCoopac = idCoopac;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Short getIndAutorizado() {
		return indAutorizado;
	}

	public void setIndAutorizado(Short indAutorizado) {
		this.indAutorizado = indAutorizado;
	}

	public Short getIndActivo() {
		return indActivo;
	}

	public void setIndActivo(Short indActivo) {
		this.indActivo = indActivo;
	}
	
	
	public String getDesToken() {
		return desToken;
	}

	public void setDesToken(String desToken) {
		this.desToken = desToken;
	}	
	
}