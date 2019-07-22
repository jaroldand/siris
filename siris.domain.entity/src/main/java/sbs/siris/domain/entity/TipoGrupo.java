package sbs.siris.domain.entity;

import sbs.siris.domain.entity.base.BaseEntity;

public class TipoGrupo extends BaseEntity {
	private String codGrupo;

	private String colDestino;

	private Short indActivo;

	public String getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getColDestino() {
		return colDestino;
	}

	public void setColDestino(String colDestino) {
		this.colDestino = colDestino;
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
		TipoGrupo other = (TipoGrupo) that;
		return (this.getCodGrupo() == null ? other.getCodGrupo() == null
				: this.getCodGrupo().equals(other.getCodGrupo()))
				&& (this.getColDestino() == null ? other.getColDestino() == null
						: this.getColDestino().equals(other.getColDestino()))
				&& (this.getIndActivo() == null ? other.getIndActivo() == null
						: this.getIndActivo().equals(other.getIndActivo()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCodGrupo() == null) ? 0 : getCodGrupo().hashCode());
		result = prime * result + ((getColDestino() == null) ? 0 : getColDestino().hashCode());
		result = prime * result + ((getIndActivo() == null) ? 0 : getIndActivo().hashCode());
		return result;
	}
}