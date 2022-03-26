package com.glauco.glauco.controllers;


import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.glauco.glauco.models.PosicaoAtivo;
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
	private PosicaoAtivoRepository posicaoRep;
	
	/**
	 * CONSTRÓI a VIEW PRINCIPAL
	 * 
	 */
	@RequestMapping("/interno")
	public ModelAndView CarregaPagina() {
		ModelAndView pagina = new ModelAndView("area-logada/area-logada");
		Iterable<PosicaoAtivo> listaAtivos = posicaoRep.findAll();
		pagina.addObject("listaAtivos", listaAtivos);
		return pagina;
		
	}
	
	
}
