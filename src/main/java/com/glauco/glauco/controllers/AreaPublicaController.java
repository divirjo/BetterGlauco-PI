package com.glauco.glauco.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * CONTROLLER DA ÁREA DE ACESSO PÙBLICO
 *
 * Controller das páginas que ficarão disponíveis ao público em geral
 *
 */
@Controller
public class AreaPublicaController {

	
	/**
	 * VIEW INDEX -> LOGIN DO SISTEMA
	 * 
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
		
	}	
	
	
	/**
	 * VIEW SOMOS
	 * Página com as informações do sistma
	 * 
	 */
	@RequestMapping("/sobre")
	public String quemSomos() {
		return "sobre";
	
	}
	
	/**
	 * VIEW COTANTO
	 * Página com formulário para contato
	 * 
	 */
	
	@RequestMapping("/contato")
	public String contato() {
		return "contato";
	

	}
	
}
