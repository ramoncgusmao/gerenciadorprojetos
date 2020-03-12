package com.ramon.gerenciadorprojetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramon.gerenciadorprojetos.domain.model.Projeto;
import com.ramon.gerenciadorprojetos.repository.ProjetoRepository;
import com.ramon.gerenciadorprojetos.service.exception.DataIntegrityException;
import com.ramon.gerenciadorprojetos.service.exception.ObjectNotFoundException;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository repository;
	
	@Autowired
	private TarefaService tarefaService;
	
	public Projeto save(Projeto projeto) {
		// TODO Auto-generated method stub
		return repository.save(projeto);
	}

	public List<Projeto> find() {
		// TODO Auto-generated method stub
		return repository.findAllByOrderByPrevisaoEntregaAsc();
	}

	public Projeto findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Projeto> projetoOpt = repository.findById(id);
		if(projetoOpt.isPresent()) {
			return projetoOpt.get();
		}
		throw new ObjectNotFoundException("id do projeto não encontrado");
	}

	public Projeto updade(Integer id, Projeto projeto) {
		Projeto projetoAtual = findById(id);
		projetoAtual.setPrevisaoEntrega(projeto.getPrevisaoEntrega());
		projetoAtual.setTitulo(projeto.getTitulo());
		
		return repository.save(projetoAtual);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
			
		}catch (Exception e) {
			throw new DataIntegrityException("não foi possivel deletar o id = " + id + " por causa: " + e.getMessage());
		}
		
	}

	public boolean projetoComTarefa(Integer id) {
		
		
		return tarefaService.existsByProjeto(id);
	}

	
}
