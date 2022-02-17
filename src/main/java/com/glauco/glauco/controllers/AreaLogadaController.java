package com.glauco.glauco.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AreaLogadaController {

	
	@RequestMapping(value = "/interno")
	public ModelAndView listaVagas() {
		ModelAndView pagina = new ModelAndView("area-logada");

		return pagina;
	}
}
