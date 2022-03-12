package com.glauco.glauco.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glauco.glauco.models.InstituicaoFinanceira;
import com.glauco.glauco.repository.InstituicaoFinanceiraRepository;

/**
 * CONTROLLER INSTITUIÇÂO FINANCEIRA
 * Controller responsável pela configuração das corretoras e bancos
 *
 */
@Controller
public class InstituicaoFinanceiraController {
	
	@Autowired
	private InstituicaoFinanceiraRepository instituicaoRep;
	
	/**
	 * CONSTRÓI a VIEW PRINCIPAL
	 * responsável por listar as instituições
	 * 
	 */
	@RequestMapping("/config-instituicao-financeira")
	public ModelAndView listaInstituicoes() {
		ModelAndView pagina = new ModelAndView("configuracoes/InstituicaoFinanceira/lista-instituicoes");
		Iterable<InstituicaoFinanceira> listaInstituicoesFinanceiras = instituicaoRep.findAll();
		pagina.addObject("listaInstituicoes", listaInstituicoesFinanceiras);
		return pagina;
	}
	
	
	/**
	 * INSERIR NOVAS INSTITUIÇÕES
	 * formulario de inclusão
	 * 
	 */
	@RequestMapping("/config-nova-instituicao")
	public String novaInstituicaoFinanceira() {
		return "configuracoes/InstituicaoFinanceira/form-instituicao";
	}
	
	
	@RequestMapping(value = "/config-nova-instituicao", method = RequestMethod.POST)
	public String form(@Valid InstituicaoFinanceira instituicao, BindingResult resultado, RedirectAttributes atributos) {

		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-nova-instituicao";
		}

		instituicaoRep.save(instituicao);
		atributos.addFlashAttribute("mensagem", "Instituição Financeira cadastrada com sucesso!");
		return "redirect:/config-instituicao-financeira";
	}

	
	/**
	 * APAGAR INSTITUIÇÃO
	 * @see substituir este método pelo botão inativar caixa
	 * 
	 */
	@RequestMapping("/config-deletar-instituicao")
	public String deletarInstituicao(int id) {
		InstituicaoFinanceira instituicao = instituicaoRep.findById(id);
		instituicaoRep.delete(instituicao);
		return "redirect:/config-instituicao-financeira";
	}
	
	
	/**
	 * ATUALIZAR CAIXA
	 * 
	 * 
	 */
	@RequestMapping("/config-editar-instituicao")
	public ModelAndView editarInstituicao(int id) {
		InstituicaoFinanceira dadosInstituicoes = instituicaoRep.findById(id);
		ModelAndView pagina = new ModelAndView("configuracoes/instituicaoFinanceira/update-instituicoes");
		pagina.addObject("instituicao", dadosInstituicoes);
		return pagina;
	}
	
	// POST que atualiza a Instituição
	@RequestMapping(value = "/config-editar-instituicao", method = RequestMethod.POST)
	public String updateFuncionario(@Valid InstituicaoFinanceira instituicao,  BindingResult resultado, RedirectAttributes atributos){
		
		int idInstituicao = instituicao.getId();
		String id = "" + idInstituicao;
		
		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem_erro", "Verifique os campos");
			return "redirect:/config-editar-instituicao?id=" + id;	
		}
		
		instituicaoRep.save(instituicao);
		atributos.addFlashAttribute("mensagem", "Instituição alterada com sucesso!");

		return "redirect:/config-editar-instituicao?id=" + id;	
	}
	
	
}
