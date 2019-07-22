package sbs.siris.domain.entity;

import java.util.Date;

import sbs.siris.domain.entity.base.BaseEntity;

public class Notificacion extends BaseEntity {
    private Long idNotificacion;

    private Integer idEvento;

    private String tipNotificacion;

    private String estNotificacion;

    private String desNotificacion;

    private Date fecEnvio;

    private Date fecRecibido;

    private Long idDocumento;

    private String codFormato;

    private String txtEnviado;

    private Short indActivo;

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipNotificacion() {
        return tipNotificacion;
    }

    public void setTipNotificacion(String tipNotificacion) {
        this.tipNotificacion = tipNotificacion;
    }

    public String getEstNotificacion() {
        return estNotificacion;
    }

    public void setEstNotificacion(String estNotificacion) {
        this.estNotificacion = estNotificacion;
    }

    public String getDesNotificacion() {
        return desNotificacion;
    }

    public void setDesNotificacion(String desNotificacion) {
        this.desNotificacion = desNotificacion;
    }

    public Date getFecEnvio() {
        return fecEnvio;
    }

    public void setFecEnvio(Date fecEnvio) {
        this.fecEnvio = fecEnvio;
    }

    public Date getFecRecibido() {
        return fecRecibido;
    }

    public void setFecRecibido(Date fecRecibido) {
        this.fecRecibido = fecRecibido;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getCodFormato() {
        return codFormato;
    }

    public void setCodFormato(String codFormato) {
        this.codFormato = codFormato;
    }

    public String getTxtEnviado() {
        return txtEnviado;
    }

    public void setTxtEnviado(String txtEnviado) {
        this.txtEnviado = txtEnviado;
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
        Notificacion other = (Notificacion) that;
        return (this.getIdNotificacion() == null ? other.getIdNotificacion() == null : this.getIdNotificacion().equals(other.getIdNotificacion()))
            && (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getTipNotificacion() == null ? other.getTipNotificacion() == null : this.getTipNotificacion().equals(other.getTipNotificacion()))
            && (this.getEstNotificacion() == null ? other.getEstNotificacion() == null : this.getEstNotificacion().equals(other.getEstNotificacion()))
            && (this.getDesNotificacion() == null ? other.getDesNotificacion() == null : this.getDesNotificacion().equals(other.getDesNotificacion()))
            && (this.getFecEnvio() == null ? other.getFecEnvio() == null : this.getFecEnvio().equals(other.getFecEnvio()))
            && (this.getFecRecibido() == null ? other.getFecRecibido() == null : this.getFecRecibido().equals(other.getFecRecibido()))
            && (this.getIdDocumento() == null ? other.getIdDocumento() == null : this.getIdDocumento().equals(other.getIdDocumento()))
            && (this.getCodFormato() == null ? other.getCodFormato() == null : this.getCodFormato().equals(other.getCodFormato()))
            && (this.getTxtEnviado() == null ? other.getTxtEnviado() == null : this.getTxtEnviado().equals(other.getTxtEnviado()))
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
        result = prime * result + ((getIdNotificacion() == null) ? 0 : getIdNotificacion().hashCode());
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getTipNotificacion() == null) ? 0 : getTipNotificacion().hashCode());
        result = prime * result + ((getEstNotificacion() == null) ? 0 : getEstNotificacion().hashCode());
        result = prime * result + ((getDesNotificacion() == null) ? 0 : getDesNotificacion().hashCode());
        result = prime * result + ((getFecEnvio() == null) ? 0 : getFecEnvio().hashCode());
        result = prime * result + ((getFecRecibido() == null) ? 0 : getFecRecibido().hashCode());
        result = prime * result + ((getIdDocumento() == null) ? 0 : getIdDocumento().hashCode());
        result = prime * result + ((getCodFormato() == null) ? 0 : getCodFormato().hashCode());
        result = prime * result + ((getTxtEnviado() == null) ? 0 : getTxtEnviado().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}