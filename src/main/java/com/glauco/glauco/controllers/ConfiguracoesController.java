package com.glauco.glauco.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * CONTROLLER CONFIGURACOES
 * Controller que reúne as configurações do sistema
 *
 */
@Controller
public class ConfiguracoesController {

	/**
	 * CONSTRÓI a VIEW PRINCIPAL
	 * 
	 */
	@RequestMapping("/config")
	public String CarregaPagina() {
		return "configuracoes/configuracoes";
		
	}	
	

	
}
