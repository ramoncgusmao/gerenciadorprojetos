package com.ramon.gerenciadorprojetos.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.gerenciadorprojetos.domain.dto.ProjetoDto;
import com.ramon.gerenciadorprojetos.domain.model.Projeto;
import com.ramon.gerenciadorprojetos.service.ProjetoService;

@RestController
@RequestMapping("/projeto")
public class ProjetoResource {
	
	@Autowired
	private ProjetoService service;
	
	@PostMapping()
	public ResponseEntity save(@RequestBody @Valid ProjetoDto dto) {
		
		try {
			Projeto projeto = dto.convertToEntity();
			projeto = service.save(projeto);
			return new ResponseEntity<Projeto>(projeto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity find() {
		
		try {
			List<Projeto> projeto = service.find();
			return ResponseEntity.ok(projeto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable("{id}") Integer id) {
		
		try {
			Projeto projeto = service.findById(id);
			return ResponseEntity.ok(projeto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("{id}") Integer id, @RequestBody @Valid ProjetoDto dto) {
		
		try {
			Projeto projeto = dto.convertToEntity();
		    projeto = service.updade(id, projeto);
			return ResponseEntity.ok(projeto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("{id}") Integer id) {
		
		try {
		    service.delete(id);
			return ResponseEntity.ok("deletado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
