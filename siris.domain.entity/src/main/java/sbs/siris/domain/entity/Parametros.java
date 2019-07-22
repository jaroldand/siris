package sbs.siris.domain.entity;

import java.util.Arrays;

import sbs.siris.domain.entity.base.BaseEntity;

public class Parametros extends BaseEntity{
    private String idParametro;

    private String tipParametro;

    private String desParametro;

    private String valor1;

    private Short indActivo;

    private byte[] valor0;

    public String getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(String idParametro) {
        this.idParametro = idParametro;
    }

    public String getTipParametro() {
        return tipParametro;
    }

    public void setTipParametro(String tipParametro) {
        this.tipParametro = tipParametro;
    }

    public String getDesParametro() {
        return desParametro;
    }

    public void setDesParametro(String desParametro) {
        this.desParametro = desParametro;
    }

    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public Short getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(Short indActivo) {
        this.indActivo = indActivo;
    }

    public byte[] getValor0() {
        return valor0;
    }

    public void setValor0(byte[] valor0) {
        this.valor0 = valor0;
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
        Parametros other = (Parametros) that;
        return (this.getIdParametro() == null ? other.getIdParametro() == null : this.getIdParametro().equals(other.getIdParametro()))
            && (this.getTipParametro() == null ? other.getTipParametro() == null : this.getTipParametro().equals(other.getTipParametro()))
            && (this.getDesParametro() == null ? other.getDesParametro() == null : this.getDesParametro().equals(other.getDesParametro()))
            && (this.getValor1() == null ? other.getValor1() == null : this.getValor1().equals(other.getValor1()))
            && (this.getIndActivo() == null ? other.getIndActivo() == null : this.getIndActivo().equals(other.getIndActivo()))
            && (Arrays.equals(this.getValor0(), other.getValor0()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdParametro() == null) ? 0 : getIdParametro().hashCode());
        result = prime * result + ((getTipParametro() == null) ? 0 : getTipParametro().hashCode());
        result = prime * result + ((getDesParametro() == null) ? 0 : getDesParametro().hashCode());
        result = prime * result + ((getValor1() == null) ? 0 : getValor1().hashCode());
        result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
        result = prime * result + (Arrays.hashCode(getValor0()));
        return result;
    }
}