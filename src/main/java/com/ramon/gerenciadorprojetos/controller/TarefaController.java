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

import com.ramon.gerenciadorprojetos.domain.dto.TarefaDto;
import com.ramon.gerenciadorprojetos.domain.model.Tarefa;
import com.ramon.gerenciadorprojetos.service.ProjetoService;
import com.ramon.gerenciadorprojetos.service.TarefaService;





@Controller
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private ProjetoService projetoService;
	@GetMapping("/cadastrar/{projetoId}")
	public String cadastrar(TarefaDto tarefa,ModelMap model,  @PathVariable("projetoId") Integer projetoId) {
		tarefa = new TarefaDto();
		tarefa.setProjeto(projetoId);
		model.addAttribute("tarefa", tarefa);
		return "tarefa/form";
	}
	
	@GetMapping("/listar/{id}")
	public String listar(ModelMap model, @PathVariable("id") Integer projetoId) {
		model.addAttribute("tarefas", tarefaService.findByProjeto(projetoId));
		model.addAttribute("projetoId", projetoId);
		model.addAttribute("mensagem", "Você realmente deseja excluir este tarefa?");
		return "tarefa/lista";
	}
	
	@PostMapping("/salvar/")
	public String salvar(@Valid TarefaDto dto,BindingResult result, RedirectAttributes attr) {
		
		Tarefa tarefa = dto.convertToEntity();
		tarefa.setProjeto(projetoService.findById(tarefa.getProjeto().getId()));
	
		if(result.hasErrors()) {
			return "tarefa/form";
		}
	
		tarefaService.save(tarefa);
		attr.addFlashAttribute("success", "tarefa inserido com sucesso.");
		return "redirect:/tarefas/listar/" + tarefa.getProjeto().getId();
	}
	

	
	@GetMapping("/editar/{tarefaId}")
	public String preEditar(@PathVariable("tarefaId") Integer tarefaId, ModelMap model) {
	
		try {
			Tarefa tarefa = tarefaService.findById(tarefaId);
			TarefaDto dto = new TarefaDto(tarefa.getId(), tarefa.getTitulo(), tarefa.getProjeto().getId());
			model.addAttribute("tarefa", dto);
			
			return "tarefa/form";
		}catch (Exception e) {
			model.addAttribute("fail", "Tarefa não existe");
			return "/";
			}
			
		
		
	}
	
	@PostMapping("/editar")
	public String editar(@Valid TarefaDto dto, BindingResult result, RedirectAttributes attr){
		
		if(result.hasErrors()) {
			return "tarefa/form";
		}
		
		if(dto.getId() == null) {
			attr.addAttribute("fail", "tarefa não existe");
		}else if( tarefaService.findById(dto.getId()) == null) {
			attr.addAttribute("fail", "tarefa não existe");
		}
		else {
			Tarefa tarefa = dto.convertToEntity();
			tarefa.setId(dto.getId());
			tarefa.setProjeto(projetoService.findById(tarefa.getProjeto().getId()));
		tarefaService.updade(tarefa.getId(),tarefa);
		attr.addFlashAttribute("success", "tarefa editado com sucesso");
		return "redirect:/tarefas/listar/" + tarefa.getProjeto().getId();
		}
		return "redirect:/";
	}
	
	@GetMapping("/excluir/{tarefaId}")
	public String excluir(@PathVariable("tarefaId") Integer id, RedirectAttributes attr) {
		
			tarefaService.delete(id);
			attr.addAttribute("success", "Tarefa excluido com sucesso");
		
		return "redirect:/";
	}
}
