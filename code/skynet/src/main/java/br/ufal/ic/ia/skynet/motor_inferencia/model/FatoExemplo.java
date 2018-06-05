package br.ufal.ic.ia.skynet.motor_inferencia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FatoExemplo {

	@Id
	@GeneratedValue
	private int id;
	
	private String descricao;
	
	public FatoExemplo(String descricao) {
		this.descricao = descricao;
	}
	
	public FatoExemplo() {}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
