package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Correo extends BaseEntity {
    private Long idCorreo;

    private Integer idEntidad;

    private Short idTabla;

    private String tipCorreo;

    private String desCorreo;

    private Short indActivo;

    private Long idNotificacion;

    public Long getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(Long idCorreo) {
        this.idCorreo = idCorreo;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Short getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Short idTabla) {
        this.idTabla = idTabla;
    }

    public String getTipCorreo() {
        return tipCorreo;
    }

    public void setTipCorreo(String tipCorreo) {
        this.tipCorreo = tipCorreo;
    }

    public String getDesCorreo() {
        return desCorreo;
    }

    public void setDesCorreo(String desCorreo) {
        this.desCorreo = desCorreo;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
    }
    
    public Long getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
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
        Correo other = (Correo) that;
        return (this.getIdCorreo() == null ? other.getIdCorreo() == null : this.getIdCorreo().equals(other.getIdCorreo()))
            && (this.getIdEntidad() == null ? other.getIdEntidad() == null : this.getIdEntidad().equals(other.getIdEntidad()))
            && (this.getIdTabla() == null ? other.getIdTabla() == null : this.getIdTabla().equals(other.getIdTabla()))
            && (this.getTipCorreo() == null ? other.getTipCorreo() == null : this.getTipCorreo().equals(other.getTipCorreo()))
            && (this.getDesCorreo() == null ? other.getDesCorreo() == null : this.getDesCorreo().equals(other.getDesCorreo()))
            && (this.getIndActivo() == null ? other.getIndActivo() == null : this.getIndActivo().equals(other.getIndActivo()))
            && (this.getIdNotificacion() == null ? other.getIdNotificacion() == null : this.getIdNotificacion().equals(other.getIdNotificacion()))
            && (this.getAuditUserCrea() == null ? other.getAuditUserCrea() == null : this.getAuditUserCrea().equals(other.getAuditUserCrea()))
            && (this.getAuditFecCrea() == null ? other.getAuditFecCrea() == null : this.getAuditFecCrea().equals(other.getAuditFecCrea()))
            && (this.getAuditUserMod() == null ? other.getAuditUserMod() == null : this.getAuditUserMod().equals(other.getAuditUserMod()))
            && (this.getAuditFecMod() == null ? other.getAuditFecMod() == null : this.getAuditFecMod().equals(other.getAuditFecMod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdCorreo() == null) ? 0 : getIdCorreo().hashCode());
        result = prime * result + ((getIdEntidad() == null) ? 0 : getIdEntidad().hashCode());
        result = prime * result + ((getIdTabla() == null) ? 0 : getIdTabla().hashCode());
        result = prime * result + ((getTipCorreo() == null) ? 0 : getTipCorreo().hashCode());
        result = prime * result + ((getDesCorreo() == null) ? 0 : getDesCorreo().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getIdNotificacion() == null) ? 0 : getIdNotificacion().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}