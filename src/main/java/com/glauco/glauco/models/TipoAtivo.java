package com.glauco.glauco.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Entity 
public class TipoAtivo implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String sigla;
	
	@NotEmpty
	private String nome;
	
	@PositiveOrZero
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPercentualAlocacaoTeorica() {
		return percentualAlocacaoTeorica;
	}

	public void setPercentualAlocacaoTeorica(float percentualAlocacaoTeorica) {
		this.percentualAlocacaoTeorica = percentualAlocacaoTeorica;
	}
	

}
