package br.ufal.ic.ia.skynet.motor_inferencia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Celular {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	
	public Celular() {
		
	}
	
	public Celular(String name) {
		this.name = name;
	} 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
