package com.glauco.glauco.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.glauco.glauco.models.Ativo;
import com.glauco.glauco.models.Caixa;
import com.glauco.glauco.models.TipoAtivo;
import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.models.InstituicaoFinanceira;
import com.glauco.glauco.repository.AtivoRepository;
import com.glauco.glauco.repository.CaixaRepository;
import com.glauco.glauco.repository.InstituicaoFinanceiraRepository;
import com.glauco.glauco.repository.TipoAtivoRepository;
import com.glauco.glauco.repository.PosicaoAtivoRepository;

/**
 * CONTROLLER ATIVO
 * Controller responsável pela configuração dos ativos
 *
 */
@Controller
public class AtivoController {

	@Autowired
	private AtivoRepository ativoRep;
	
	@Autowired
	private CaixaRepository caixaRep;
	
	@Autowired
	private InstituicaoFinanceiraRepository instituicaoRep;
	
	@Autowired
	private TipoAtivoRepository tipoRep;
	
	@Autowired
	private PosicaoAtivoRepository posicaoRep;
	
	@RequestMapping("/config-ativo")
	public ModelAndView listaTipos() {
		ModelAndView pagina = new ModelAndView("configuracoes/ativos/lista-ativos");
		Iterable<Ativo> listaAtivos = ativoRep.findAll();
		pagina.addObject("listaAtivos", listaAtivos);
		return pagina;
	}
	
	
	@RequestMapping("/config-novo-ativo")
	public ModelAndView novoAtivo() {	
		ModelAndView pagina = new ModelAndView("configuracoes/ativos/novo-ativo");
		
		Iterable<Caixa> listaCaixas = caixaRep.findAll();
		pagina.addObject("listaCaixas", listaCaixas);
		
		Iterable<TipoAtivo> listaTiposAtivo = tipoRep.findAll();
		pagina.addObject("listaTiposAtivo", listaTiposAtivo);
		
		Iterable<InstituicaoFinanceira> listaInstituicoesFinanceiras = instituicaoRep.findAll();
		pagina.addObject("listaCorretoras", listaInstituicoesFinanceiras);
		
		return pagina;
	}
	
	
	@RequestMapping(value = "/config-novo-ativo", method = RequestMethod.POST)
	public String form(@Valid Ativo ativo, BindingResult resultado, RedirectAttributes atributos, 
			@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data, 
			@RequestParam("valorInvestido") Float valorInvestido) {

		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-novo-ativo";
		}
		
		ativoRep.save(ativo);
		
		/*
		 * Salva a posição inicial do ativo
		 */
		PosicaoAtivo posicaoInicial = new PosicaoAtivo();
		
		posicaoInicial.setAtivo(ativo);
		posicaoInicial.setData(data);
		posicaoInicial.aporteInvestimento(valorInvestido);
		posicaoRep.save(posicaoInicial);
		
		atributos.addFlashAttribute("mensagem", "Ativo cadastrado com sucesso");
		return "redirect:/config-novo-ativo";
	
	
	
	}

	
	@RequestMapping("/config-deletar-ativo")
	public String deletarAtivo(int id) {
		Ativo ativo = ativoRep.findById(id);
		ativoRep.delete(ativo);
		return "redirect:/config-ativo";
	}
	
	
	@RequestMapping("/config-editar-ativo")
	public ModelAndView editarAtivo(int id) {
		Ativo dadosAtivo = ativoRep.findById(id);
		ModelAndView pagina = new ModelAndView("configuracoes/ativos/update-ativo");
		
		pagina.addObject("ativo", dadosAtivo);
		
		Iterable<Caixa> listaCaixas = caixaRep.findAll();
		pagina.addObject("listaCaixas", listaCaixas);
		
		Iterable<TipoAtivo> listaTiposAtivo = tipoRep.findAll();
		pagina.addObject("listaTiposAtivo", listaTiposAtivo);
		
		Iterable<InstituicaoFinanceira> listaInstituicoesFinanceiras = instituicaoRep.findAll();
		pagina.addObject("listaCorretoras", listaInstituicoesFinanceiras);
		
		return pagina;
	}
	
	
	@RequestMapping(value = "/config-editar-ativo", method = RequestMethod.POST)
	public String updateFuncionario(@Valid Ativo ativo,  BindingResult resultado, RedirectAttributes atributos){
		
		int idAtivo = ativo.getId();
		String id = "" + idAtivo;
		
		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-editar-ativo?id=" + id;	
		}
		
		ativoRep.save(ativo);		
	
		atributos.addFlashAttribute("mensagem", "Ativo atualizado com sucesso");

		return "redirect:/config-editar-ativo?id=" + id;	
	}
}
