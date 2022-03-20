package com.glauco.glauco.controllers;

import java.util.Date;
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
import com.glauco.glauco.models.Ativo;
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
	 * Carrega a página inicialmente
	 */
	@RequestMapping("/atualizar-investimentos")
	public ModelAndView listaTipos() {
		ModelAndView pagina = new ModelAndView("area-logada/atualiza-investimentos");
		
		Iterable<InstituicaoFinanceira> listaInstituicoesFinanceiras = instituicaoRep.findAll();	
		pagina.addObject("listaCorretoras", listaInstituicoesFinanceiras);
		pagina.addObject("corretoraAtiva",instituicaoRep.findFirstByOrderByNomeAsc());
		
		return pagina;
	}
	
		
	@RequestMapping(value = "/atualizar-investimentos", method = RequestMethod.POST)
	public ModelAndView carregaInvestimentos(@RequestParam(name ="corretora") String idCorretora) {

		InstituicaoFinanceira corretoraAtiva = instituicaoRep.findById(Integer.parseInt(idCorretora)); 
		ModelAndView pagina = new ModelAndView("area-logada/atualiza-investimentos");
	
		
		Iterable<Ativo> listaInvestimentosCorretora = ativoRep.findByCorretora(corretoraAtiva);
		
		List<PosicaoAtivo> valoresAtuais = new ArrayList<PosicaoAtivo>();		
		
		listaInvestimentosCorretora.forEach((corretora)->{
			PosicaoAtivo Posicao = posicaoRep.findTop1ByAtivoOrderByDataDesc(corretora);
			valoresAtuais.add(Posicao);
		});	
		
		pagina.addObject("listaInvestimentosCorretora", valoresAtuais);
		
		Iterable<InstituicaoFinanceira> listaInstituicoesFinanceiras = instituicaoRep.findAll();	
		pagina.addObject("listaCorretoras", listaInstituicoesFinanceiras);
		pagina.addObject("CorretoraAtiva", corretoraAtiva);
			
		return pagina;
		}
		
}
