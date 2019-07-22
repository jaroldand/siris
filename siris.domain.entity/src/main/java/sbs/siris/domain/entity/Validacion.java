package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Validacion extends BaseEntity {
    private Integer idValidacion;

    private Integer idEvento;

    private String tipoValidacion;

    private String isSelected;
    
    private boolean selectedBol;
    

    public Validacion() {
    	
    }
    
    public Validacion(Integer idEvento, String tipoValidacion, String isSelected) {
		//super();
		this.idEvento = idEvento;
		this.tipoValidacion = tipoValidacion;
		this.isSelected = isSelected;
	}

    public Integer getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(Integer idValidacion) {
        this.idValidacion = idValidacion;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipoValidacion() {
        return tipoValidacion;
    }

    public void setTipoValidacion(String tipoValidacion) {
        this.tipoValidacion = tipoValidacion;
    }

    public String getIsSelected() {
        return isSelected;
    }
    
    public boolean getSelectedBol() {
    	selectedBol = "1".equals( this.isSelected ) ? true : false;
		return selectedBol;
	}

	public void setSelectedBol(boolean selectedBol) {
		this.selectedBol = selectedBol;
	}

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
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
        Validacion other = (Validacion) that;
        return (this.getIdValidacion() == null ? other.getIdValidacion() == null : this.getIdValidacion().equals(other.getIdValidacion()))
            && (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getTipoValidacion() == null ? other.getTipoValidacion() == null : this.getTipoValidacion().equals(other.getTipoValidacion()))
            && (this.getIsSelected() == null ? other.getIsSelected() == null : this.getIsSelected().equals(other.getIsSelected()))
            && (this.getAuditUserCrea() == null ? other.getAuditUserCrea() == null : this.getAuditUserCrea().equals(other.getAuditUserCrea()))
            && (this.getAuditFecCrea() == null ? other.getAuditFecCrea() == null : this.getAuditFecCrea().equals(other.getAuditFecCrea()))
            && (this.getAuditUserMod() == null ? other.getAuditUserMod() == null : this.getAuditUserMod().equals(other.getAuditUserMod()))
            && (this.getAuditFecMod() == null ? other.getAuditFecMod() == null : this.getAuditFecMod().equals(other.getAuditFecMod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdValidacion() == null) ? 0 : getIdValidacion().hashCode());
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getTipoValidacion() == null) ? 0 : getTipoValidacion().hashCode());
        result = prime * result + ((getIsSelected() == null) ? 0 : getIsSelected().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}