package sbs.siris.domain.entity.dto;

import java.io.Serializable;

public class ResultDTO implements Serializable {

	private static final long serialVersionUID = -9003212747195673481L;
	
	private int codigo;
	private String message;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
