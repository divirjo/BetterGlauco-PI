package com.glauco.glauco.controllers;

import java.util.Date;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glauco.glauco.models.Ativo;
import com.glauco.glauco.models.Caixa;
import com.glauco.glauco.models.CaixaValorTotal;
import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.repository.PosicaoAtivoRepository;
import com.glauco.glauco.repository.CaixaRepository;

/**
 * CONTROLLER DO DASH - caixas de investimento
 *
 *
 */
@Controller
public class DashCaixaController {
	
	@Autowired
	private PosicaoAtivoRepository posicaoRep;
	
	@Autowired
	private CaixaRepository caixaRep;
	
	@RequestMapping(value = "/dash-caixa")
	public ModelAndView geraDash(int id) {

		ModelAndView pagina = new ModelAndView("area-logada/dash-caixa");
		
		/*
		 * Monta o menu caixas de investimento
		 */
		Iterable<Caixa> listaCaixas = caixaRep.findAll();
		pagina.addObject("listaCaixas", listaCaixas);
		
		List<CaixaValorTotal> listaTotalCaixas = new ArrayList<CaixaValorTotal>();
		CaixaValorTotal totalCaixa;
		
		Iterable<PosicaoAtivo> listaPosicoesCaixa;
		for (Caixa caixa:listaCaixas) {
			listaPosicoesCaixa = posicaoRep.findAtivoByAtivoCaixaOrderByAtivoSiglaAscDataDesc(caixa);
			totalCaixa = new CaixaValorTotal(listaPosicoesCaixa, 
					Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant() ));
			listaTotalCaixas.add(totalCaixa);
		}
		pagina.addObject("listaTotalCaixas", listaTotalCaixas);
		
		/*
		 * Monta a view
		 */
		Caixa caixaSelecionada = caixaRep.findById(id);
		pagina.addObject("caixaAtiva", caixaSelecionada);
		
		listaPosicoesCaixa = posicaoRep.findAtivoByAtivoCaixaOrderByAtivoSiglaAscDataDesc(caixaSelecionada);
		
		List<PosicaoAtivo> listaAtivosCaixa = new ArrayList<PosicaoAtivo>();
		int idAtivoAnterior = 0;
		
		for (PosicaoAtivo posicao:listaPosicoesCaixa) {
			if (idAtivoAnterior != posicao.getAtivo().getId()){
				idAtivoAnterior = posicao.getAtivo().getId();
				listaAtivosCaixa.add(posicao);
			}
		}
		
		pagina.addObject("listaAtivos", listaAtivosCaixa);
		
	
		return pagina;
	}
	
	
	
}
