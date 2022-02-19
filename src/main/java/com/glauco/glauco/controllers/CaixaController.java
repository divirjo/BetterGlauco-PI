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

@Controller
public class CaixaController {
	
	@Autowired
	private CaixaRepository caixaRep;
	
	// Constrói a view com o formulário de cadastrar uma nova caixa
	@RequestMapping("/config/cadastrarCaixa")
	public String form() {
		return "configuracoes/caixas/lista-caixas";
	}
	
	
	@RequestMapping(value = "/config/cadastrarCaixa", method = RequestMethod.POST)
	public String form(@Valid Caixa caixa, BindingResult resultado, RedirectAttributes atributos) {

		if (resultado.hasErrors()) {
			atributos.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/config/cadastrarCaixa";
		}

		caixaRep.save(caixa);
		atributos.addFlashAttribute("mensagem", "Empresa cadastrada com sucesso!");
		return "redirect:/config/caixas";
	}

	// Constrói a view Listar Caixas
	@RequestMapping(value = "/config/caixas")
	public ModelAndView listaCaixas() {
		ModelAndView pagina = new ModelAndView("configuracoes/caixas/lista-caixas");
		Iterable<Caixa> listaCaixas = caixaRep.findAll();
		pagina.addObject("listaCaixas", listaCaixas);
		return pagina;
	}
	
	//GET que deleta caixa
	@RequestMapping("/config/deletarCaixa")
	public String deletarCaixa(int id) {
		Caixa caixa = caixaRep.findById(id);
		caixaRep.delete(caixa);
		return "redirect:/config/caixas";
	}
	
	// GET que chama o FORM de edição da empresa
	@RequestMapping("/config/editar-caixa")
	public ModelAndView editarEmpresa(int id) {
		Caixa caixa = caixaRep.findById(id);
		ModelAndView pagina = new ModelAndView("configuracoes/caixas/updade-caixa");
		pagina.addObject("caixa", caixa);
		return pagina;
	}
	
	// POST que atualiza a caixa --> TRATAR ERROS, conforme a inclusão
	@RequestMapping(value = "/editar-empresa", method = RequestMethod.POST)
	public String updateFuncionario(@Valid Caixa caixa,  BindingResult result, RedirectAttributes attributes){
		
		caixaRep.save(caixa);
		attributes.addFlashAttribute("success", "Caixa alterada com sucesso!");
		
		long idLong = caixa.getId();
		String id = "" + idLong;
		return "redirect:/detalhes-caixa/" + id;	
	}
	
	
}
