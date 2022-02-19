package com.glauco.glauco.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


@Entity 
public class Caixa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private float alocacaoTeorica;
	
	@NotEmpty
	private float percentualAlocacaoTeorica;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getAlocacaoTeorica() {
		return alocacaoTeorica;
	}

	public void setAlocacaoTeorica(float alocacaoTeorica) {
		this.alocacaoTeorica = alocacaoTeorica;
	}

	public float getPercentualAlocacaoTeorica() {
		return percentualAlocacaoTeorica;
	}

	public void setPercentualAlocacaoTeorica(float percentualAlocacaoTeorica) {
		this.percentualAlocacaoTeorica = percentualAlocacaoTeorica;
	}

	
	
	
	
	
	
}
