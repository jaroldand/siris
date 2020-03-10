package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Canales extends BaseEntity {
    private Integer idCanales;

    private Integer idEvento;

    private String tipCanalGrupo;

    private String tipCanalDetalle;
    
    private String descCanalDetalle;

    private String indCondNormal;

    private Short indActivo;
    
    private Situacion situacion;
    
    //for reportes
    private String descCanalDetalleStr;

    public Integer getIdCanales() {
        return idCanales;
    }

    public void setIdCanales(Integer idCanales) {
        this.idCanales = idCanales;
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

    public String getIndCondNormal() {
        return indCondNormal;
    }

    public void setIndCondNormal(String indCondNormal) {
        this.indCondNormal = indCondNormal;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
    }
    
    public String getDescCanalDetalle() {
		return descCanalDetalle;
	}

	public void setDescCanalDetalle(String descCanalDetalle) {
		this.descCanalDetalle = descCanalDetalle;
	}
	
	public String getDescCanalDetalleStr() {
		return descCanalDetalleStr;
	}

	public void setDescCanalDetalleStr(String descCanalDetalleStr) {
		this.descCanalDetalleStr = descCanalDetalleStr;
	}
	
	public Situacion getSituacion() {
		return situacion;
	}

	public void setSituacion(Situacion situacion) {
		this.situacion = situacion;
	}

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Canales other = (Canales) that;
        return (this.getIdCanales() == null ? other.getIdCanales() == null : this.getIdCanales().equals(other.getIdCanales()))
            && (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getTipCanalGrupo() == null ? other.getTipCanalGrupo() == null : this.getTipCanalGrupo().equals(other.getTipCanalGrupo()))
            && (this.getTipCanalDetalle() == null ? other.getTipCanalDetalle() == null : this.getTipCanalDetalle().equals(other.getTipCanalDetalle()))
            && (this.getIndCondNormal() == null ? other.getIndCondNormal() == null : this.getIndCondNormal().equals(other.getIndCondNormal()))
            && (this.getIndActivo() == null ? other.getIndActivo() == null : this.getIndActivo().equals(other.getIndActivo()))
            && (this.getAuditUserCrea() == null ? other.getAuditUserCrea() == null : this.getAuditUserCrea().equals(other.getAuditUserCrea()))
            && (this.getAuditFecCrea() == null ? other.getAuditFecCrea() == null : this.getAuditFecCrea().equals(other.getAuditFecCrea()))
            && (this.getAuditUserMod() == null ? other.getAuditUserMod() == null : this.getAuditUserMod().equals(other.getAuditUserMod()))
            && (this.getAuditFecMod() == null ? other.getAuditFecMod() == null : this.getAuditFecMod().equals(other.getAuditFecMod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdCanales() == null) ? 0 : getIdCanales().hashCode());
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getTipCanalGrupo() == null) ? 0 : getTipCanalGrupo().hashCode());
        result = prime * result + ((getTipCanalDetalle() == null) ? 0 : getTipCanalDetalle().hashCode());
        result = prime * result + ((getIndCondNormal() == null) ? 0 : getIndCondNormal().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}