package sbs.siris.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClaveValor {

	private String clave;

	private String valor;

	private String valor1;

	private String valor2;

	@JsonProperty("key")
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@JsonProperty("value")
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@JsonProperty("value1")
	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	@JsonProperty("value2")
	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

}