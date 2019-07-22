package sbs.siris.domain.entity;

import java.util.Arrays;

import sbs.siris.domain.entity.base.BaseEntity;

public class File extends BaseEntity{

	private Long idFile;
    
    private Integer idEvento;

    private String desDocumento;

    private String tipDocumento;

    private String desExtension;

    private Long numTamanio;

    private Short indActivo;

    private byte[] binArchivo;

    public Long getIdFile() {
        return idFile;
    }

    public void setIdFile(Long idFile) {
        this.idFile = idFile;
    }

    public String getDesDocumento() {
        return desDocumento;
    }

    public void setDesDocumento(String desDocumento) {
        this.desDocumento = desDocumento;
    }

    public String getTipDocumento() {
        return tipDocumento;
    }

    public void setTipDocumento(String tipDocumento) {
        this.tipDocumento = tipDocumento;
    }

    public String getDesExtension() {
        return desExtension;
    }

    public void setDesExtension(String desExtension) {
        this.desExtension = desExtension;
    }

    public Long getNumTamanio() {
        return numTamanio;
    }

    public void setNumTamanio(Long numTamanio) {
        this.numTamanio = numTamanio;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
    }

    public byte[] getBinArchivo() {
        return binArchivo;
    }

    public void setBinArchivo(byte[] binArchivo) {
        this.binArchivo = binArchivo;
    }
    
    public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
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
        File other = (File) that;
        return (this.getIdFile() == null ? other.getIdFile() == null : this.getIdFile().equals(other.getIdFile()))
            && (this.getDesDocumento() == null ? other.getDesDocumento() == null : this.getDesDocumento().equals(other.getDesDocumento()))
            && (this.getTipDocumento() == null ? other.getTipDocumento() == null : this.getTipDocumento().equals(other.getTipDocumento()))
            && (this.getDesExtension() == null ? other.getDesExtension() == null : this.getDesExtension().equals(other.getDesExtension()))
            && (this.getNumTamanio() == null ? other.getNumTamanio() == null : this.getNumTamanio().equals(other.getNumTamanio()))
            && (this.getIndActivo() == null ? other.getIndActivo() == null : this.getIndActivo().equals(other.getIndActivo()))
            && (this.getAuditUserCrea() == null ? other.getAuditUserCrea() == null : this.getAuditUserCrea().equals(other.getAuditUserCrea()))
            && (this.getAuditFecCrea() == null ? other.getAuditFecCrea() == null : this.getAuditFecCrea().equals(other.getAuditFecCrea()))
            && (this.getAuditUserMod() == null ? other.getAuditUserMod() == null : this.getAuditUserMod().equals(other.getAuditUserMod()))
            && (this.getAuditFecMod() == null ? other.getAuditFecMod() == null : this.getAuditFecMod().equals(other.getAuditFecMod()))
            && (Arrays.equals(this.getBinArchivo(), other.getBinArchivo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdFile() == null) ? 0 : getIdFile().hashCode());
        result = prime * result + ((getDesDocumento() == null) ? 0 : getDesDocumento().hashCode());
        result = prime * result + ((getTipDocumento() == null) ? 0 : getTipDocumento().hashCode());
        result = prime * result + ((getDesExtension() == null) ? 0 : getDesExtension().hashCode());
        result = prime * result + ((getNumTamanio() == null) ? 0 : getNumTamanio().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        result = prime * result + (Arrays.hashCode(getBinArchivo()));
        return result;
    }
}