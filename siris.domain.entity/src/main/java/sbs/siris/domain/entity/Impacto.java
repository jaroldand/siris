package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class Impacto extends BaseEntity{
    
	private Integer idImpacto;

    private Integer idEvento;

    private String tipoImpacto;

    private String descripcion;

    private String impactoDetail;

    private Short indActivo;
    
    //for reporte
    private String impactoDetailStr;
    private String labelReport;
    
    public Impacto() {
    	
    }
    
    public Impacto(String tipoImpacto) {
		this.tipoImpacto = tipoImpacto;
	}

    public Integer getIdImpacto() {
        return idImpacto;
    }

    public void setIdImpacto(Integer idImpacto) {
        this.idImpacto = idImpacto;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipoImpacto() {
        return tipoImpacto;
    }

    public void setTipoImpacto(String tipoImpacto) {
        this.tipoImpacto = tipoImpacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImpactoDetail() {
        return impactoDetail;
    }

    public void setImpactoDetail(String impactoDetail) {
        this.impactoDetail = impactoDetail;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
    }
    
    public String getImpactoDetailStr() {
		return impactoDetailStr;
	}

	public void setImpactoDetailStr(String impactoDetailStr) {
		this.impactoDetailStr = impactoDetailStr;
	}
	
	public String getLabelReport() {
		return labelReport;
	}

	public void setLabelReport(String labelReport) {
		this.labelReport = labelReport;
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
        Impacto other = (Impacto) that;
        return (this.getIdImpacto() == null ? other.getIdImpacto() == null : this.getIdImpacto().equals(other.getIdImpacto()))
            && (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getTipoImpacto() == null ? other.getTipoImpacto() == null : this.getTipoImpacto().equals(other.getTipoImpacto()))
            && (this.getDescripcion() == null ? other.getDescripcion() == null : this.getDescripcion().equals(other.getDescripcion()))
            && (this.getImpactoDetail() == null ? other.getImpactoDetail() == null : this.getImpactoDetail().equals(other.getImpactoDetail()))
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
        result = prime * result + ((getIdImpacto() == null) ? 0 : getIdImpacto().hashCode());
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getTipoImpacto() == null) ? 0 : getTipoImpacto().hashCode());
        result = prime * result + ((getDescripcion() == null) ? 0 : getDescripcion().hashCode());
        result = prime * result + ((getImpactoDetail() == null) ? 0 : getImpactoDetail().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}