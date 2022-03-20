package com.glauco.glauco.controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.models.TipoAtivo;
import com.glauco.glauco.models.Ativo;
import com.glauco.glauco.models.Caixa;
import com.glauco.glauco.models.InstituicaoFinanceira;
import com.glauco.glauco.repository.PosicaoAtivoRepository;
import com.glauco.glauco.repository.AtivoRepository;
import com.glauco.glauco.repository.InstituicaoFinanceiraRepository;

/**
 * CONTROLLER ATIVO
 * Controller responsável pela atualização dos investimentos
 *
 */
@Controller
public class AtualizaInvestimentosController {
	
	@Autowired
	private PosicaoAtivoRepository posicaoRep;
	
	@Autowired
	private AtivoRepository ativoRep;
	
	@Autowired
	private InstituicaoFinanceiraRepository instituicaoRep;

	
	
	/*
	 * (1) SELECAO DA CORRETORA
	 */
	@RequestMapping("/atualizar-investimentos")
	public ModelAndView apresentaCorretoras() {
		ModelAndView pagina = new ModelAndView("area-logada/atualiza-ativos/lista-corretoras");
		
		Iterable<InstituicaoFinanceira> listaCorretoras = ativoRep.findDistinctByCorretora();
		pagina.addObject("listaCorretorasAtivos", listaCorretoras);
		
		return pagina;
	}
	
	/*
	 * (2) SELECAO DO ATIVO
	 */	
	@RequestMapping("/atualizar-investimentos-ativos")
	public ModelAndView exibirAtivos(int id) {
		
		ModelAndView pagina = new ModelAndView("area-logada/atualiza-ativos/lista-ativos");
		
		InstituicaoFinanceira corretoraSelecionada = instituicaoRep.findById(id);
		
		Iterable<PosicaoAtivo> ativosCorretora = posicaoRep.findAtivoByAtivoCorretoraOrderByAtivoSigla(corretoraSelecionada);
		
		List<PosicaoAtivo> posicoes = new ArrayList<PosicaoAtivo>();
		PosicaoAtivo posicaoAtual = new PosicaoAtivo();
		int idAtivoAnterior = 0;
		
		for (PosicaoAtivo posicao:ativosCorretora) {
			if (idAtivoAnterior != posicao.getAtivo().getId()){
				idAtivoAnterior = posicao.getAtivo().getId();
				posicaoAtual = posicaoRep.findTop1ByAtivoOrderByDataDesc(posicao.getAtivo());
				posicoes.add(posicaoAtual);
			}
		}
		
		pagina.addObject("listaAtivos", posicoes);
		
		
		return pagina;
	}
	
	/*
	 * (3) ATUALIZAR POSICAO DO ATIVO
	 */	
	@RequestMapping("/atualizar-posicao-ativo")
	public ModelAndView atualizaPosicao(int id) {
		
		ModelAndView pagina = new ModelAndView("area-logada/atualiza-ativos/nova-posicao");
		Ativo ativo = ativoRep.findById(id);
		pagina.addObject("ativoSelecionado", ativo);
		
		Iterable<PosicaoAtivo> listaPosicoes = posicaoRep.findByAtivoOrderByDataDesc(ativo);
		pagina.addObject("historico", listaPosicoes);
		
		
		return pagina;
	}
	
	@RequestMapping(value = "/atualizar-posicao-ativo", method = RequestMethod.POST)
	public String form(RedirectAttributes atributos,
			@RequestParam("idAtivo") int idAtivo,
			@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data, 
			@RequestParam("valorAtual") Float valorAtual,
			@RequestParam("aporte") Float valorAporte) {

	
		Ativo ativo = ativoRep.findById(idAtivo);
		PosicaoAtivo posicaoAnterior = posicaoRep.findTop1ByAtivoOrderByDataDesc(ativo);
		PosicaoAtivo novaPosicao = new PosicaoAtivo();

		if (!data.after(posicaoAnterior.getData())) {
			atributos.addFlashAttribute("mensagem_erro", "Erro. Data deve ser superior à cadastrada anteriormente");
			return "redirect:/atualizar-posicao-ativo?id="+ ativo.getId();
		}
			
		
		novaPosicao.setAtivo(ativo);
		novaPosicao.setData(data);
		novaPosicao.setCotas(posicaoAnterior.getCotas());
		novaPosicao.setValorTotal(valorAtual);
		novaPosicao.aporteInvestimento(valorAporte);
		posicaoRep.save(novaPosicao);
		
		
		atributos.addFlashAttribute("mensagem", "Posicação atualizada com sucesso");
		return "redirect:/atualizar-posicao-ativo?id="+ ativo.getId();
	
	
	
	}
	
	
		
}
