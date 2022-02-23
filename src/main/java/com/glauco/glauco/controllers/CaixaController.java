package com.glauco.glauco.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glauco.glauco.models.Caixa;
import com.glauco.glauco.repository.CaixaRepository;

/**
 * CONTROLLER CAIXAS
 * Controller responsável pela configuração das caixas / categorias de investimento
 *
 */
@Controller
public class CaixaController {
	
	@Autowired
	private CaixaRepository caixaRep;
	
	/**
	 * CONSTRÓI a VIEW PRINCIPAL
	 * responsável por listar as caixas
	 * 
	 */
	@RequestMapping("/config-caixas")
	public ModelAndView listaCaixas() {
		ModelAndView pagina = new ModelAndView("configuracoes/caixas/lista-caixas");
		Iterable<Caixa> listaCaixas = caixaRep.findAll();
		pagina.addObject("listaCaixas", listaCaixas);
		return pagina;
	}
	
	
	/**
	 * INSERIR NOVAS CAIXAS
	 * formulario de inclusacao
	 * 
	 */
	@RequestMapping("/config-nova-caixa")
	public String novaCaixa() {
		return "configuracoes/caixas/form-caixa";
	}
	
	
	@RequestMapping(value = "/config-nova-caixa", method = RequestMethod.POST)
	public String form(@Valid Caixa caixa, BindingResult resultado, RedirectAttributes atributos) {

		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-nova-caixa";
		}

		caixaRep.save(caixa);
		atributos.addFlashAttribute("mensagem", "Caixa cadastrada com sucesso!");
		return "redirect:/config-caixas";
	}

	
	/**
	 * APAGAR CAIXA
	 * @see substituir este método pelo botão inativar caixa
	 * 
	 */
	@RequestMapping("/config-deletar-caixa")
	public String deletarCaixa(int id) {
		Caixa caixa = caixaRep.findById(id);
		caixaRep.delete(caixa);
		return "redirect:/config-caixas";
	}
	
	
	/**
	 * ATUALIZAR CAIXA
	 * 
	 * 
	 */
	@RequestMapping("/config-editar-caixa")
	public ModelAndView editarCaixa(int id) {
		Caixa dadosCaixa = caixaRep.findById(id);
		ModelAndView pagina = new ModelAndView("configuracoes/caixas/update-caixa");
		pagina.addObject("caixa", dadosCaixa);
		return pagina;
	}
	
	// POST que atualiza a caixa
	@RequestMapping(value = "/config-editar-caixa", method = RequestMethod.POST)
	public String updateFuncionario(@Valid Caixa caixa,  BindingResult resultado, RedirectAttributes atributos){
		
		int idCaixa = caixa.getId();
		String id = "" + idCaixa;
		
		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-editar-caixa?id=" + id;	
		}
		
		caixaRep.save(caixa);
		atributos.addFlashAttribute("mensagem", "Caixa alterada com sucesso!");

		return "redirect:/config-editar-caixa?id=" + id;	
	}
	
	
}
