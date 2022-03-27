package com.glauco.glauco.controllers;


import java.util.Date;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.glauco.glauco.models.Caixa;
import com.glauco.glauco.models.CaixaValorTotal;
import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.repository.CaixaRepository;
import com.glauco.glauco.repository.PosicaoAtivoRepository;

/**
 * CONTROLLER PRINCIPAL DO SISTEMA
 *
 * Controller da página do dashboard principal
 *
 */
@Controller
public class AreaLogadaController {

	@Autowired
	private CaixaRepository caixaRep;
	
	@Autowired
	private PosicaoAtivoRepository posicaoRep;
	
	/**
	 * CONSTRÓI a VIEW PRINCIPAL
	 * 
	 */
	@RequestMapping("/interno")
	public ModelAndView CarregaPagina() {
		ModelAndView pagina = new ModelAndView("area-logada/area-logada");
		
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
	
		Iterable<PosicaoAtivo> listaAtivos = posicaoRep.findAllOrderByDataDesc();
	    Date dataAnterior = null;
	    List totalAtivos = new ArrayList<>();
	    List<CaixaValorTotal> linha = new ArrayList<CaixaValorTotal>();
		for (PosicaoAtivo posicaoAtual:listaAtivos) {
			if (dataAnterior == null) {
				dataAnterior = posicaoAtual.getData();
			}
			
			if (posicaoAtual.getData().equals(dataAnterior)) {
				
			}
		}
		
		pagina.addObject("listaTotalAtivos", totalAtivos);
			 */
		return pagina;
		
	}
	
	
}
