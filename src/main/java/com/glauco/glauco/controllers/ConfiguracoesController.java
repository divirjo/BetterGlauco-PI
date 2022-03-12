package com.glauco.glauco.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.glauco.glauco.repository.CaixaRepository;
import com.glauco.glauco.repository.InstituicaoFinanceiraRepository;
import com.glauco.glauco.repository.TipoAtivoRepository;

/**
 * CONTROLLER CONFIGURACOES
 * Controller que reúne as configurações do sistema
 *
 */
@Controller
public class ConfiguracoesController {

	@Autowired
	private CaixaRepository caixaRep;
	
	@Autowired
	private InstituicaoFinanceiraRepository instituicaoRep;
	
	@Autowired
	private TipoAtivoRepository tipoRep;
	
	/**
	 * CONSTRÓI a VIEW PRINCIPAL
	 * 
	 */
	@RequestMapping("/config")
	public String CarregaPagina() {
		return "configuracoes/configuracoes";
		
	}	
	
	/**
	 * BUSCA CONFIGURACAO
	 * 
	 */
	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public ModelAndView pesquisar(@RequestParam("buscar") String buscar) {
		
		ModelAndView pagina = new ModelAndView("configuracoes/configuracoes");
		
		pagina.addObject("mensagem", "Resultados da busca por " + buscar);
		
		pagina.addObject("caixasLocalizadas", caixaRep.findByNomes(buscar));
		
		pagina.addObject("instituicoesLocalizadas", instituicaoRep.findByNomes(buscar));
		
		pagina.addObject("tiposAtivoLocalizados", tipoRep.findByNomes(buscar));
		
		return pagina;
		
	}

	
}
