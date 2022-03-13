package com.glauco.glauco.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.models.Ativo;

import com.glauco.glauco.repository.PosicaoAtivoRepository;
import com.glauco.glauco.repository.AtivoRepository;

/**
 * CONTROLLER ATIVO
 * Controller responsável pela atualização dos investimentos
 *
 */
@Controller
public class AtualizaInvestimentosController {
	
	@Autowired
	private PosicaoAtivoRepository posicaoRep;

	@RequestMapping("/atualizar-investimentos")
	public ModelAndView listaTipos() {
		ModelAndView pagina = new ModelAndView("area-logada/atualiza-investimentos");
		Iterable<PosicaoAtivo> posicoes = posicaoRep.findAll();
		pagina.addObject("posicoesAtivo", posicoes);
		return pagina;
	}
	
	
}
