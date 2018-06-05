package br.ufal.ic.ia.skynet.motor_inferencia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fato {

	@Id
	@GeneratedValue
	private int id;

	private String descricao;
	
	public Fato(String descricao) {
		this.descricao = descricao;
	}
	
	public Fato() {}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
