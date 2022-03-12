package com.glauco.glauco.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glauco.glauco.models.TipoAtivo;
import com.glauco.glauco.repository.TipoAtivoRepository;


/**
 * CONTROLLER TIPO ATIVO
 * Controller responsável pela configuração dos tipos /classes de ativos
 *  ex: FII, FDIC, Ação, BRD, etc.
 *
 */
@Controller
public class TipoAtivoController {

	@Autowired
	private TipoAtivoRepository tipoRep;
	
	@RequestMapping("/config-tipo-ativo")
	public ModelAndView listaTipos() {
		ModelAndView pagina = new ModelAndView("configuracoes/tipoAtivo/lista-tipo-ativo");
		Iterable<TipoAtivo> listaTipos = tipoRep.findAll();
		pagina.addObject("listaTiposAtivos", listaTipos);
		return pagina;
	}
	
	
	@RequestMapping("/config-novo-tipo-ativo")
	public String novoTipoAtivo() {
		return "configuracoes/tipoAtivo/form-tipo-ativo";
	}
	
	
	@RequestMapping(value = "/config-novo-tipo-ativo", method = RequestMethod.POST)
	public String form(@Valid TipoAtivo tipo, BindingResult resultado, RedirectAttributes atributos) {

		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-novo-tipo-ativo";
		}

		tipoRep.save(tipo);
		atributos.addFlashAttribute("mensagem", "Tipo de ativo cadastrado com sucesso!");
		return "redirect:/config-novo-tipo-ativo";
	}

	
	@RequestMapping("/config-deletar-tipo-ativo")
	public String deletarTipoAtivo(int id) {
		TipoAtivo tipo = tipoRep.findById(id);
		tipoRep.delete(tipo);
		return "redirect:/config-tipo-ativo";
	}
	
	
	@RequestMapping("/config-editar-tipo-ativo")
	public ModelAndView editarTipoAtivo(int id) {
		TipoAtivo dadosTipoAtivo = tipoRep.findById(id);
		ModelAndView pagina = new ModelAndView("configuracoes/tipoAtivo/update-tipo-ativo");
		pagina.addObject("tipoAtivo", dadosTipoAtivo);
		return pagina;
	}
	
	
	@RequestMapping(value = "/config-editar-tipo-ativo", method = RequestMethod.POST)
	public String updateFuncionario(@Valid TipoAtivo tipo,  BindingResult resultado, RedirectAttributes atributos){
		
		int idTipoAtivo = tipo.getId();
		String id = "" + idTipoAtivo;
		
		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-editar-tipo-ativo?id=" + id;	
		}
		
		tipoRep.save(tipo);
		atributos.addFlashAttribute("mensagem", "Tipo de ativo alterado com sucesso!");

		return "redirect:/config-editar-tipo-ativo?id=" + id;	
	}
	
}
