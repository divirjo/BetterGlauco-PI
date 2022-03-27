package com.glauco.glauco.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class CaixaValorTotal {

	private Caixa caixa;
	
	private float valorTotal;
	
	private float valorInvestido;
	
	private float rentabilidade;
	
	private float rentabilidadeRelativa;
		
	public CaixaValorTotal() {
		
		this.valorTotal = 0;
		this.valorInvestido = 0;
		this.rentabilidade = 0;
		this.rentabilidadeRelativa = 0;
	}

	public CaixaValorTotal (Iterable<PosicaoAtivo> listaPosicoesCaixa, Date dataLimite) {
		
		this.valorTotal = 0;
		this.valorInvestido = 0;
		this.rentabilidade = 0;
		this.rentabilidadeRelativa = 0;
		
		
		
		PosicaoAtivo ativoProcessado = new PosicaoAtivo();
		boolean calculoRelativo = false , primeiroLoop = true;
		float valorInvestidoParcial = 0;
		
		for (PosicaoAtivo posicaoAtual:listaPosicoesCaixa) {
			
			if (primeiroLoop) {
				this.caixa = posicaoAtual.getAtivo().getCaixa();
			}
			
			//Localiza a posição do ativo igual ou anterior a data limite
			if (!dataLimite.before(posicaoAtual.getData())) {
				if (primeiroLoop) {
					this.valorTotal = posicaoAtual.getValorTotal();
					primeiroLoop = false;
					calculoRelativo = true;
				}
				else {
					//Verifica se alterou o ativo
					if (ativoProcessado.getAtivo().getId() != posicaoAtual.getAtivo().getId()){
						this.valorTotal += posicaoAtual.getValorTotal();
						calculoRelativo = true;
					}
					//Calculos relativos à posição anterior
					else {	
						if (calculoRelativo) {
							if(ativoProcessado.getCotas() != posicaoAtual.getCotas()) {
								valorInvestidoParcial = ativoProcessado.getCotas() - posicaoAtual.getCotas();
								valorInvestidoParcial *= ativoProcessado.getValorCota();
								this.valorInvestido += valorInvestidoParcial;
							}
							this.rentabilidade += (ativoProcessado.getValorCota() - posicaoAtual.getValorCota())
									* posicaoAtual.getCotas();
							calculoRelativo = false;
						}
					}
				}
				ativoProcessado = posicaoAtual;			
			}		
		}
	}
	
	
	public Caixa getCaixa() {
		return caixa;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public float getValorInvestido() {
		return valorInvestido;
	}

	public float getRentabilidade() {
		return rentabilidade;
	}

	public float getRentabilidadeRelativa() {
		return rentabilidadeRelativa;
	}
	
	public void stRentabilidadeRelativa(float rentabilidadeRelativa) {
		this.rentabilidadeRelativa = rentabilidadeRelativa;
	}
	
	
}
