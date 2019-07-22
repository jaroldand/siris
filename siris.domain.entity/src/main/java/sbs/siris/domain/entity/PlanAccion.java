package sbs.siris.domain.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import sbs.siris.domain.entity.base.BaseEntity;

public class PlanAccion extends BaseEntity{
    private Integer idPlanAccion;

    private Integer idEvento;

    private String descPlan;

    private String areaResp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private Date fecImplementacion;
    
    private String fecImplementacionStr;

    private Short filePlanAcc;

    private Short indActivo;
    
    //for the report
    private Integer tbl_index;

    public Integer getIdPlanAccion() {
        return idPlanAccion;
    }

    public void setIdPlanAccion(Integer idPlanAccion) {
        this.idPlanAccion = idPlanAccion;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getDescPlan() {
        return descPlan;
    }

    public void setDescPlan(String descPlan) {
        this.descPlan = descPlan;
    }

    public String getAreaResp() {
        return areaResp;
    }

    public void setAreaResp(String areaResp) {
        this.areaResp = areaResp;
    }

    public Date getFecImplementacion() {
        return fecImplementacion;
    }

    public void setFecImplementacion(Date fecImplementacion) {
        this.fecImplementacion = fecImplementacion;
    }

    public Short getFilePlanAcc() {
        return filePlanAcc;
    }

    public void setFilePlanAcc(Short filePlanAcc) {
        this.filePlanAcc = filePlanAcc;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
    }
    
    public String getFecImplementacionStr() {
		return fecImplementacionStr;
	}

	public void setFecImplementacionStr(String fecImplementacionStr) {
		this.fecImplementacionStr = fecImplementacionStr;
	}
	
	public Integer getTbl_index() {
		return tbl_index;
	}

	public void setTbl_index(Integer tbl_index) {
		this.tbl_index = tbl_index;
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
        PlanAccion other = (PlanAccion) that;
        return (this.getIdPlanAccion() == null ? other.getIdPlanAccion() == null : this.getIdPlanAccion().equals(other.getIdPlanAccion()))
            && (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getDescPlan() == null ? other.getDescPlan() == null : this.getDescPlan().equals(other.getDescPlan()))
            && (this.getAreaResp() == null ? other.getAreaResp() == null : this.getAreaResp().equals(other.getAreaResp()))
            && (this.getFecImplementacion() == null ? other.getFecImplementacion() == null : this.getFecImplementacion().equals(other.getFecImplementacion()))
            && (this.getFilePlanAcc() == null ? other.getFilePlanAcc() == null : this.getFilePlanAcc().equals(other.getFilePlanAcc()))
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
        result = prime * result + ((getIdPlanAccion() == null) ? 0 : getIdPlanAccion().hashCode());
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getDescPlan() == null) ? 0 : getDescPlan().hashCode());
        result = prime * result + ((getAreaResp() == null) ? 0 : getAreaResp().hashCode());
        result = prime * result + ((getFecImplementacion() == null) ? 0 : getFecImplementacion().hashCode());
        result = prime * result + ((getFilePlanAcc() == null) ? 0 : getFilePlanAcc().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}