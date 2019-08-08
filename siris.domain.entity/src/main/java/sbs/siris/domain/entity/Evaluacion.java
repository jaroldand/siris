package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Evaluacion extends BaseEntity{
    private Integer idEvaluacion;

    private Integer idEvento;

    private String tipoEvaluacion;

    private String respEvaluacion;

    private Short indActivo;

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getRespEvaluacion() {
        return respEvaluacion;
    }

    public void setRespEvaluacion(String respEvaluacion) {
        this.respEvaluacion = respEvaluacion;
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
        Evaluacion other = (Evaluacion) that;
        return (this.getIdEvaluacion() == null ? other.getIdEvaluacion() == null : this.getIdEvaluacion().equals(other.getIdEvaluacion()))
            && (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getTipoEvaluacion() == null ? other.getTipoEvaluacion() == null : this.getTipoEvaluacion().equals(other.getTipoEvaluacion()))
            && (this.getRespEvaluacion() == null ? other.getRespEvaluacion() == null : this.getRespEvaluacion().equals(other.getRespEvaluacion()))
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
        result = prime * result + ((getIdEvaluacion() == null) ? 0 : getIdEvaluacion().hashCode());
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getTipoEvaluacion() == null) ? 0 : getTipoEvaluacion().hashCode());
        result = prime * result + ((getRespEvaluacion() == null) ? 0 : getRespEvaluacion().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}