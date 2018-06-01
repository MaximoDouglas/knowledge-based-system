package br.ufal.ic.ia.skynet.motor_inferencia.model;

public class Fatos {

	private String name = "";
	
	public Fatos(String descricao) {
		this.name = descricao;
	}

	public String getDescricao() {
		return name;
	}

	public void setDescricao(String descricao) {
		this.name = descricao;
	}
	
}
