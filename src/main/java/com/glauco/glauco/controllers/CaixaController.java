package com.glauco.glauco.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CaixaController {

	
	@RequestMapping(value = "/config/caixas")
	public ModelAndView listaCaixas() {
		ModelAndView pagina = new ModelAndView("configuracoes/caixas/lista-caixas");

		return pagina;
	}
}
