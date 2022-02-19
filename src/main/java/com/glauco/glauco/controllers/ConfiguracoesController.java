package com.glauco.glauco.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ConfiguracoesController {

	@RequestMapping(value = "/config")
	public ModelAndView carregaMenu() {
		ModelAndView pagina = new ModelAndView("configuracoes/configuracoes");

		return pagina;
	}
	
}
