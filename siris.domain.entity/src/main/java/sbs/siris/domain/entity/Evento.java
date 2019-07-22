package sbs.siris.domain.entity;

import java.util.Date;

import sbs.siris.domain.entity.base.BaseEntity;

public class Evento extends BaseEntity{
    private Integer idEvento;

    private String idEntidadVig;

    private String idEstado;

    private String codAnio;

    private Date fechaEvento;

    private String msgFinalizacion;

    private Short indActivo;

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

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getCodAnio() {
        return codAnio;
    }

    public void setCodAnio(String codAnio) {
        this.codAnio = codAnio;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getMsgFinalizacion() {
        return msgFinalizacion;
    }

    public void setMsgFinalizacion(String msgFinalizacion) {
        this.msgFinalizacion = msgFinalizacion;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
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
        Evento other = (Evento) that;
        return (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getIdEntidadVig() == null ? other.getIdEntidadVig() == null : this.getIdEntidadVig().equals(other.getIdEntidadVig()))
            && (this.getIdEstado() == null ? other.getIdEstado() == null : this.getIdEstado().equals(other.getIdEstado()))
            && (this.getCodAnio() == null ? other.getCodAnio() == null : this.getCodAnio().equals(other.getCodAnio()))
            && (this.getFechaEvento() == null ? other.getFechaEvento() == null : this.getFechaEvento().equals(other.getFechaEvento()))
            && (this.getMsgFinalizacion() == null ? other.getMsgFinalizacion() == null : this.getMsgFinalizacion().equals(other.getMsgFinalizacion()))
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
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getIdEntidadVig() == null) ? 0 : getIdEntidadVig().hashCode());
        result = prime * result + ((getIdEstado() == null) ? 0 : getIdEstado().hashCode());
        result = prime * result + ((getCodAnio() == null) ? 0 : getCodAnio().hashCode());
        result = prime * result + ((getFechaEvento() == null) ? 0 : getFechaEvento().hashCode());
        result = prime * result + ((getMsgFinalizacion() == null) ? 0 : getMsgFinalizacion().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}