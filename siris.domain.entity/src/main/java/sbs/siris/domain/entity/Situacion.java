package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Situacion extends BaseEntity {
    
	private Integer idEvento;
	
	private Long idSituacion;
    
    private Integer idCanales;
    
    private String tipoSituacion;
    
    private String descripcion;
    
    private Short indActivo;
    
    //for reportes
    private String tipCanalGrupo;
    
    private String tipCanalDetalle;
    
    private String descCanalDetalle;
    
    private String descTipoSituacion;

	public Long getIdSituacion() {
		return idSituacion;
	}

	public void setIdSituacion(Long idSituacion) {
		this.idSituacion = idSituacion;
	}

	public Integer getIdCanales() {
		return idCanales;
	}

	public void setIdCanales(Integer idCanales) {
		this.idCanales = idCanales;
	}

	public String getTipoSituacion() {
		return tipoSituacion;
	}

	public void setTipoSituacion(String tipoSituacion) {
		this.tipoSituacion = tipoSituacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Short getIndActivo() {
		return indActivo;
	}

	public void setIndActivo(Short indActivo) {
		this.indActivo = indActivo;
	}

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getTipCanalGrupo() {
		return tipCanalGrupo;
	}

	public void setTipCanalGrupo(String tipCanalGrupo) {
		this.tipCanalGrupo = tipCanalGrupo;
	}

	public String getTipCanalDetalle() {
		return tipCanalDetalle;
	}

	public void setTipCanalDetalle(String tipCanalDetalle) {
		this.tipCanalDetalle = tipCanalDetalle;
	}

	public String getDescCanalDetalle() {
		return descCanalDetalle;
	}

	public void setDescCanalDetalle(String descCanalDetalle) {
		this.descCanalDetalle = descCanalDetalle;
	}

	public String getDescTipoSituacion() {
		return descTipoSituacion;
	}

	public void setDescTipoSituacion(String descTipoSituacion) {
		this.descTipoSituacion = descTipoSituacion;
	}

    
}