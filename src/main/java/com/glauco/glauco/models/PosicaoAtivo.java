package com.glauco.glauco.models;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity 
public class PosicaoAtivo implements Serializable{

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@PositiveOrZero
	private float cotas;
	
	@PositiveOrZero
	private float valorCota;
	
	@ManyToOne
	private Ativo ativo;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getCotas() {
		return cotas;
	}

	//public void setCotas(float cotas) {
	//	this.cotas = cotas;
	//}

	public float getValorCota() {
		return valorCota;
	}

	//public void setValorCotas(float valorCotas) {
	//	this.valorCotas = valorCotas;
	//}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
	public float getValorTotal() {
		return valorCota * cotas;
		
	}
	
	/* Atualiza o valor da cota, a partir do valor atual investido
	 * 
	 */
	public void setValorTotal(float valorTotal) {
		if (cotas != 0) {
			valorCota = valorTotal / cotas;
		}
		
	}
	
	/* Atualiza a quantidade de cotas, na hipótese de ocorrer um aporte
	 * de valores
	 */
	public void aporteInvestimento (float valorInvestido) {
		if (cotas == 0) {
			valorCota = 100;
		}
		float novasCotas = valorInvestido / valorCota;
		cotas += novasCotas; 
	}
	
}
