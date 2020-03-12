package com.ramon.gerenciadorprojetos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ramon.gerenciadorprojetos.domain.model.Projeto;
import com.ramon.gerenciadorprojetos.service.ProjetoService;



@Controller
@RequestMapping("/projetos")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Projeto projeto) {
		return "/projeto/form";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("projetos", projetoService.find());
		model.addAttribute("mensagem", "Você realmente deseja excluir este projeto?");
		return "projeto/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Projeto projeto, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/projeto/form";
		}
		projetoService.save(projeto);
		attr.addFlashAttribute("success", "projeto inserido com sucesso.");
		return "redirect:/projetos/listar";
	}
	

	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Integer id, ModelMap model) {
	
		try {
			model.addAttribute("projeto", projetoService.findById(id));
			return "/projeto/form";
		}catch (Exception e) {
			model.addAttribute("fail", "Projeto não existe");
			return listar(model);		}
			
		
		
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Projeto projeto, BindingResult result, RedirectAttributes attr){
		
		if(result.hasErrors()) {
			return "/projeto/form";
		}
		
		if(projeto.getId() == null) {
			attr.addAttribute("fail", "projeto não existe");
		}else if( projetoService.findById(projeto.getId()) == null) {
			attr.addAttribute("fail", "projeto não existe");
		}
		else {
		projetoService.updade(projeto.getId(),projeto);
		attr.addFlashAttribute("success", "projeto editado com sucesso");
		}
		return "redirect:/projetos/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Integer id, RedirectAttributes attr) {
		if(projetoService.projetoComTarefa(id)) {
			attr.addFlashAttribute("fail", "Projeto nao excluido. Existe tarefa(s) vinculada(s)");
		} else {
			projetoService.delete(id);
			attr.addFlashAttribute("success", "Projeto excluido com sucesso");
		}
		return "redirect:/projetos/listar";
	}
}
