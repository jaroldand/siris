package sbs.siris.domain.entity.dto;

import java.io.Serializable;

public class BandejaDTO implements Serializable {

	private static final long serialVersionUID = -5221242299163387473L;
	
	private String codigo;
	private int idEvento;
	private String descCortaEvento;
	private String fechaEventoBandeja;
	private String fechaEnvio;
	private String idEstado;
	private String descEstado;
	private String isEditar;
	private String isEliminar;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescCortaEvento() {
		return descCortaEvento;
	}
	public void setDescCortaEvento(String descCortaEvento) {
		this.descCortaEvento = descCortaEvento;
	}
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	public String getDescEstado() {
		return descEstado;
	}
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getIsEditar() {
		return isEditar;
	}
	public void setIsEditar(String isEditar) {
		this.isEditar = isEditar;
	}
	public String getIsEliminar() {
		return isEliminar;
	}
	public void setIsEliminar(String isEliminar) {
		this.isEliminar = isEliminar;
	}
	public String getFechaEventoBandeja() {
		return fechaEventoBandeja;
	}
	public void setFechaEventoBandeja(String fechaEventoBandeja) {
		this.fechaEventoBandeja = fechaEventoBandeja;
	}
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
}
