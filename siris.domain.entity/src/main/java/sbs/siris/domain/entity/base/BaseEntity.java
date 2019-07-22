package sbs.siris.domain.entity.base;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseEntity {

	private String auditUserCrea;

    private Date auditFecCrea;

    private String auditUserMod;

    private Date auditFecMod;

    @JsonIgnore
    public String getAuditUserCrea() {
        return auditUserCrea;
    }

    public void setAuditUserCrea(String auditUserCrea) {
        this.auditUserCrea = auditUserCrea;
    }

    @JsonIgnore
    public Date getAuditFecCrea() {
        return auditFecCrea;
    }

    public void setAuditFecCrea(Date auditFecCrea) {
        this.auditFecCrea = auditFecCrea;
    }

    @JsonIgnore
    public String getAuditUserMod() {
        return auditUserMod;
    }

    public void setAuditUserMod(String auditUserMod) {
        this.auditUserMod = auditUserMod;
    }

    @JsonIgnore
    public Date getAuditFecMod() {
        return auditFecMod;
    }

    public void setAuditFecMod(Date auditFecMod) {
        this.auditFecMod = auditFecMod;
    }

}