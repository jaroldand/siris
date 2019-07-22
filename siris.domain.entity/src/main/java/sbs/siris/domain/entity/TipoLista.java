package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class TipoLista extends BaseEntity {
	private String codLista;

	private String codGrupo;

	private String valor0;

	private String valor1;

	private Short indActivo;

	public String getCodLista() {
		return codLista;
	}

	public void setCodLista(String codLista) {
		this.codLista = codLista;
	}

	public String getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getValor0() {
		return valor0;
	}

	public void setValor0(String valor0) {
		this.valor0 = valor0;
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
		TipoLista other = (TipoLista) that;
		return (this.getCodLista() == null ? other.getCodLista() == null
				: this.getCodLista().equals(other.getCodLista()))
				&& (this.getCodGrupo() == null ? other.getCodGrupo() == null
						: this.getCodGrupo().equals(other.getCodGrupo()))
				&& (this.getValor0() == null ? other.getValor0() == null : this.getValor0().equals(other.getValor0()))
				&& (this.getValor1() == null ? other.getValor1() == null : this.getValor1().equals(other.getValor1()))
				&& (this.getIndActivo() == null ? other.getIndActivo() == null
						: this.getIndActivo().equals(other.getIndActivo()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCodLista() == null) ? 0 : getCodLista().hashCode());
		result = prime * result + ((getCodGrupo() == null) ? 0 : getCodGrupo().hashCode());
		result = prime * result + ((getValor0() == null) ? 0 : getValor0().hashCode());
		result = prime * result + ((getValor1() == null) ? 0 : getValor1().hashCode());
		result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
		return result;
	}
}