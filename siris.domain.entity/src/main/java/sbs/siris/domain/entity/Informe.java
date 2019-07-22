package sbs.siris.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import sbs.siris.domain.entity.base.BaseEntity;

public class Informe extends BaseEntity {
    private Integer idInforme;

    private Integer idEvento;

    private String tipEvento;

    private String isEventoSeg;
    
    private String isEventoFin;
    
    private boolean eventoSegBol;
    
    private boolean eventoFinBol;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private Date fecIniInterrupcion;
    
    private String fecIniInterrupcionStr;
    
    private String horIniInterrupcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private Date fecFinInterrupcion;
    
    private String fecFinInterrupcionStr;
    
    private String horFinInterrupcion;

    private BigDecimal totalInterrupcion;

    private BigDecimal menorInterrupcion;

    private String descCortaEvento;

    private String descEvento;

    private String causaInterrupcion;

    private String descAcciones;

    private String comentAdic;

    private Long fileComentAdic;
    
    private String nameFileComentAdic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
    private Date fecEnvioRepInicial;
    
    private String fecEnvioRepInicialStr;

    private String contactoNomb;

    private String contactoCargo;

    private String contactoTelef;

    private String contactoCorreo;

    private Short indActivo;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Lima")
    private Date fecEnvio;
    
    //for reportes
    private String tipEventoDesc;
    
    private String eventoSegDesc;
    
    private String eventoFinDesc;

    public Integer getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipEvento() {
        return tipEvento;
    }

    public void setTipEvento(String tipEvento) {
        this.tipEvento = tipEvento;
    }

    public String getIsEventoSeg() {
        return isEventoSeg;
    }

    public void setIsEventoSeg(String isEventoSeg) {
        this.isEventoSeg = isEventoSeg;
    }
    
    public boolean getEventoSegBol() {
    	eventoSegBol = "1".equals( this.isEventoSeg ) ? true : false;
		return eventoSegBol;
	}

	public void setEventoSegBol(boolean eventoSegBol) {
		this.eventoSegBol = eventoSegBol;
	}
	
	public boolean getEventoFinBol() {
		eventoFinBol = "1".equals( this.isEventoFin ) ? true : false;
		return eventoFinBol;
	}

	public void setEventoFinBol(boolean eventoFinBol) {
		this.eventoFinBol = eventoFinBol;
	}

    public Date getFecIniInterrupcion() {
        return fecIniInterrupcion;
    }

    public void setFecIniInterrupcion(Date fecIniInterrupcion) {
        this.fecIniInterrupcion = fecIniInterrupcion;
    }
    
    public String getFecIniInterrupcionStr() {
        return fecIniInterrupcionStr;
    }

    public void setFecIniInterrupcionStr(String fecIniInterrupcionStr) {
        this.fecIniInterrupcionStr = fecIniInterrupcionStr;
    }

    public Date getFecFinInterrupcion() {
        return fecFinInterrupcion;
    }

    public void setFecFinInterrupcion(Date fecFinInterrupcion) {
        this.fecFinInterrupcion = fecFinInterrupcion;
    }
    
    public String getFecFinInterrupcionStr() {
        return fecFinInterrupcionStr;
    }

    public void setFecFinInterrupcionStr(String fecFinInterrupcionStr) {
        this.fecFinInterrupcionStr = fecFinInterrupcionStr;
    }

    public BigDecimal getTotalInterrupcion() {
        return totalInterrupcion;
    }

    public void setTotalInterrupcion(BigDecimal totalInterrupcion) {
        this.totalInterrupcion = totalInterrupcion;
    }

    public BigDecimal getMenorInterrupcion() {
        return menorInterrupcion;
    }

    public void setMenorInterrupcion(BigDecimal menorInterrupcion) {
        this.menorInterrupcion = menorInterrupcion;
    }

    public String getDescCortaEvento() {
        return descCortaEvento;
    }

    public void setDescCortaEvento(String descCortaEvento) {
        this.descCortaEvento = descCortaEvento;
    }

    public String getDescEvento() {
        return descEvento;
    }

    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }

    public String getCausaInterrupcion() {
        return causaInterrupcion;
    }

    public void setCausaInterrupcion(String causaInterrupcion) {
        this.causaInterrupcion = causaInterrupcion;
    }

    public String getDescAcciones() {
        return descAcciones;
    }

    public void setDescAcciones(String descAcciones) {
        this.descAcciones = descAcciones;
    }

    public String getComentAdic() {
        return comentAdic;
    }

    public void setComentAdic(String comentAdic) {
        this.comentAdic = comentAdic;
    }

    public Long getFileComentAdic() {
        return fileComentAdic;
    }

    public void setFileComentAdic(Long fileComentAdic) {
        this.fileComentAdic = fileComentAdic;
    }

    public Date getFecEnvioRepInicial() {
        return fecEnvioRepInicial;
    }

    public void setFecEnvioRepInicial(Date fecEnvioRepInicial) {
        this.fecEnvioRepInicial = fecEnvioRepInicial;
    }

    public String getContactoNomb() {
        return contactoNomb;
    }

    public void setContactoNomb(String contactoNomb) {
        this.contactoNomb = contactoNomb;
    }

    public String getContactoCargo() {
        return contactoCargo;
    }

    public void setContactoCargo(String contactoCargo) {
        this.contactoCargo = contactoCargo;
    }

    public String getContactoTelef() {
        return contactoTelef;
    }

    public void setContactoTelef(String contactoTelef) {
        this.contactoTelef = contactoTelef;
    }

    public String getContactoCorreo() {
        return contactoCorreo;
    }

    public void setContactoCorreo(String contactoCorreo) {
        this.contactoCorreo = contactoCorreo;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
    }
    
    public String getHorIniInterrupcion() {
		return horIniInterrupcion;
	}

	public void setHorIniInterrupcion(String horIniInterrupcion) {
		this.horIniInterrupcion = horIniInterrupcion;
	}

	public String getHorFinInterrupcion() {
		return horFinInterrupcion;
	}

	public void setHorFinInterrupcion(String horFinInterrupcion) {
		this.horFinInterrupcion = horFinInterrupcion;
	}
	
	public String getFecEnvioRepInicialStr() {
		return fecEnvioRepInicialStr;
	}

	public void setFecEnvioRepInicialStr(String fecEnvioRepInicialStr) {
		this.fecEnvioRepInicialStr = fecEnvioRepInicialStr;
	}
	
	public String getNameFileComentAdic() {
		return nameFileComentAdic;
	}

	public void setNameFileComentAdic(String nameFileComentAdic) {
		this.nameFileComentAdic = nameFileComentAdic;
	}
	
	public String getIsEventoFin() {
		return isEventoFin;
	}

	public void setIsEventoFin(String isEventoFin) {
		this.isEventoFin = isEventoFin;
	}
	
	public Date getFecEnvio() {
		return fecEnvio;
	}

	public void setFecEnvio(Date fecEnvio) {
		this.fecEnvio = fecEnvio;
	}
	
	public String getTipEventoDesc() {
		return tipEventoDesc;
	}

	public void setTipEventoDesc(String tipEventoDesc) {
		this.tipEventoDesc = tipEventoDesc;
	}
	
	public String getEventoSegDesc() {
		return eventoSegDesc;
	}

	public void setEventoSegDesc(String eventoSegDesc) {
		this.eventoSegDesc = eventoSegDesc;
	}
	
	public String getEventoFinDesc() {
		return eventoFinDesc;
	}

	public void setEventoFinDesc(String eventoFinDesc) {
		this.eventoFinDesc = eventoFinDesc;
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
        Informe other = (Informe) that;
        return (this.getIdInforme() == null ? other.getIdInforme() == null : this.getIdInforme().equals(other.getIdInforme()))
            && (this.getIdEvento() == null ? other.getIdEvento() == null : this.getIdEvento().equals(other.getIdEvento()))
            && (this.getTipEvento() == null ? other.getTipEvento() == null : this.getTipEvento().equals(other.getTipEvento()))
            && (this.getIsEventoSeg() == null ? other.getIsEventoSeg() == null : this.getIsEventoSeg().equals(other.getIsEventoSeg()))
            && (this.getFecIniInterrupcion() == null ? other.getFecIniInterrupcion() == null : this.getFecIniInterrupcion().equals(other.getFecIniInterrupcion()))
            && (this.getFecFinInterrupcion() == null ? other.getFecFinInterrupcion() == null : this.getFecFinInterrupcion().equals(other.getFecFinInterrupcion()))
            && (this.getTotalInterrupcion() == null ? other.getTotalInterrupcion() == null : this.getTotalInterrupcion().equals(other.getTotalInterrupcion()))
            && (this.getMenorInterrupcion() == null ? other.getMenorInterrupcion() == null : this.getMenorInterrupcion().equals(other.getMenorInterrupcion()))
            && (this.getDescCortaEvento() == null ? other.getDescCortaEvento() == null : this.getDescCortaEvento().equals(other.getDescCortaEvento()))
            && (this.getDescEvento() == null ? other.getDescEvento() == null : this.getDescEvento().equals(other.getDescEvento()))
            && (this.getCausaInterrupcion() == null ? other.getCausaInterrupcion() == null : this.getCausaInterrupcion().equals(other.getCausaInterrupcion()))
            && (this.getDescAcciones() == null ? other.getDescAcciones() == null : this.getDescAcciones().equals(other.getDescAcciones()))
            && (this.getComentAdic() == null ? other.getComentAdic() == null : this.getComentAdic().equals(other.getComentAdic()))
            && (this.getFileComentAdic() == null ? other.getFileComentAdic() == null : this.getFileComentAdic().equals(other.getFileComentAdic()))
            && (this.getFecEnvioRepInicial() == null ? other.getFecEnvioRepInicial() == null : this.getFecEnvioRepInicial().equals(other.getFecEnvioRepInicial()))
            && (this.getContactoNomb() == null ? other.getContactoNomb() == null : this.getContactoNomb().equals(other.getContactoNomb()))
            && (this.getContactoCargo() == null ? other.getContactoCargo() == null : this.getContactoCargo().equals(other.getContactoCargo()))
            && (this.getContactoTelef() == null ? other.getContactoTelef() == null : this.getContactoTelef().equals(other.getContactoTelef()))
            && (this.getContactoCorreo() == null ? other.getContactoCorreo() == null : this.getContactoCorreo().equals(other.getContactoCorreo()))
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
        result = prime * result + ((getIdInforme() == null) ? 0 : getIdInforme().hashCode());
        result = prime * result + ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
        result = prime * result + ((getTipEvento() == null) ? 0 : getTipEvento().hashCode());
        result = prime * result + ((getIsEventoSeg() == null) ? 0 : getIsEventoSeg().hashCode());
        result = prime * result + ((getFecIniInterrupcion() == null) ? 0 : getFecIniInterrupcion().hashCode());
        result = prime * result + ((getFecFinInterrupcion() == null) ? 0 : getFecFinInterrupcion().hashCode());
        result = prime * result + ((getTotalInterrupcion() == null) ? 0 : getTotalInterrupcion().hashCode());
        result = prime * result + ((getMenorInterrupcion() == null) ? 0 : getMenorInterrupcion().hashCode());
        result = prime * result + ((getDescCortaEvento() == null) ? 0 : getDescCortaEvento().hashCode());
        result = prime * result + ((getDescEvento() == null) ? 0 : getDescEvento().hashCode());
        result = prime * result + ((getCausaInterrupcion() == null) ? 0 : getCausaInterrupcion().hashCode());
        result = prime * result + ((getDescAcciones() == null) ? 0 : getDescAcciones().hashCode());
        result = prime * result + ((getComentAdic() == null) ? 0 : getComentAdic().hashCode());
        result = prime * result + ((getFileComentAdic() == null) ? 0 : getFileComentAdic().hashCode());
        result = prime * result + ((getFecEnvioRepInicial() == null) ? 0 : getFecEnvioRepInicial().hashCode());
        result = prime * result + ((getContactoNomb() == null) ? 0 : getContactoNomb().hashCode());
        result = prime * result + ((getContactoCargo() == null) ? 0 : getContactoCargo().hashCode());
        result = prime * result + ((getContactoTelef() == null) ? 0 : getContactoTelef().hashCode());
        result = prime * result + ((getContactoCorreo() == null) ? 0 : getContactoCorreo().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + ((getAuditUserCrea() == null) ? 0 : getAuditUserCrea().hashCode());
        result = prime * result + ((getAuditFecCrea() == null) ? 0 : getAuditFecCrea().hashCode());
        result = prime * result + ((getAuditUserMod() == null) ? 0 : getAuditUserMod().hashCode());
        result = prime * result + ((getAuditFecMod() == null) ? 0 : getAuditFecMod().hashCode());
        return result;
    }
}