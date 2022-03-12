package com.glauco.glauco.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;



@Entity 
public class Ativo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String sigla;
	
	private long cnpj;
	
	private byte alocacaoTeorica;
	
	@PositiveOrZero
	private float percentualAlocacaoTeorica;
	
	@ManyToOne
	private Caixa caixa;
	
	@ManyToOne
	private TipoAtivo tipoAtivo;
	
	@ManyToOne
	private InstituicaoFinanceira corretora;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public byte getAlocacaoTeorica() {
		return alocacaoTeorica;
	}

	public void setAlocacaoTeorica(byte alocacaoTeorica) {
		this.alocacaoTeorica = alocacaoTeorica;
	}

	public float getPercentualAlocacaoTeorica() {
		return percentualAlocacaoTeorica;
	}

	public void setPercentualAlocacaoTeorica(float percentualAlocacaoTeorica) {
		this.percentualAlocacaoTeorica = percentualAlocacaoTeorica;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public TipoAtivo getTipoAtivo() {
		return tipoAtivo;
	}

	public void setTipoAtivo(TipoAtivo tipoAtivo) {
		this.tipoAtivo = tipoAtivo;
	}

	public InstituicaoFinanceira getCorretora() {
		return corretora;
	}

	public void setCorretora(InstituicaoFinanceira corretora) {
		this.corretora = corretora;
	}

	
	
}
