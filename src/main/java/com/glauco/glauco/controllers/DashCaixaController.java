package com.glauco.glauco.controllers;

import java.util.Date;
import java.util.Iterator;
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
		
		Caixa caixaSelecionada = caixaRep.findById(2);
		
		Iterable<PosicaoAtivo> listaAtivos = posicaoRep.findAtivoByAtivoCaixaOrderByAtivoSigla(caixaSelecionada);
		pagina.addObject("listaAtivos", listaAtivos);
		
	
		return pagina;
	}
}
