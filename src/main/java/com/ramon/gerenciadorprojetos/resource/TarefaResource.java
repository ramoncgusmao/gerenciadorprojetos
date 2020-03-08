package com.ramon.gerenciadorprojetos.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ramon.gerenciadorprojetos.domain.dto.TarefaDto;
import com.ramon.gerenciadorprojetos.domain.model.Tarefa;
import com.ramon.gerenciadorprojetos.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaResource {
	
	@Autowired
	private TarefaService service;
	
	@PostMapping()
	public ResponseEntity save(@RequestBody @Valid TarefaDto dto) {
		
		try {
			Tarefa tarefa = dto.convertToEntity();
			tarefa = service.save(tarefa);
			return new ResponseEntity<Tarefa>(tarefa, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity find() {
		
		try {
			List<Tarefa> tarefa = service.find();
			return ResponseEntity.ok(tarefa);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable("{id}") Integer id) {
		
		try {
			Tarefa tarefa = service.findById(id);
			return ResponseEntity.ok(tarefa);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("{id}") Integer id, @RequestBody @Valid TarefaDto dto) {
		
		try {
			Tarefa tarefa = dto.convertToEntity();
		    tarefa = service.updade(id, tarefa);
			return ResponseEntity.ok(tarefa);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
