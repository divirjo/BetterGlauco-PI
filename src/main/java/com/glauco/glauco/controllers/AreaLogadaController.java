package com.glauco.glauco.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CONTROLLER PRINCIPAL DO SISTEMA
 *
 * Controller da página do dashboard principal
 *
 */
@Controller
public class AreaLogadaController {

	
	/**
	 * CONSTRÓI a VIEW PRINCIPAL
	 * 
	 */
	@RequestMapping("/interno")
	public String CarregaPagina() {
		return "area-logada/area-logada";
		
	}
	
}
