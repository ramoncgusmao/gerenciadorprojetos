package com.ramon.gerenciadorprojetos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramon.gerenciadorprojetos.domain.model.Tarefa;
import com.ramon.gerenciadorprojetos.repository.TarefaRepository;
import com.ramon.gerenciadorprojetos.service.exception.DataIntegrityException;
import com.ramon.gerenciadorprojetos.service.exception.ObjectNotFoundException;


public class TarefaService {

	@Autowired
	private TarefaRepository repository;
	
	public Tarefa save(Tarefa tarefa) {
		// TODO Auto-generated method stub
		return repository.save(tarefa);
	}

	public List<Tarefa> find() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Tarefa findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Tarefa> tarefaOpt = repository.findById(id);
		if(tarefaOpt.isPresent()) {
			return tarefaOpt.get();
		}
		throw new ObjectNotFoundException("id do tarefa não encontrado");
	}

	public Tarefa updade(Integer id, Tarefa tarefa) {
		Tarefa tarefaAtual = findById(id);

		tarefaAtual.setTitulo(tarefa.getTitulo());
		
		return repository.save(tarefaAtual);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
			
		}catch (Exception e) {
			throw new DataIntegrityException("não foi possivel deletar o id = " + id + " por causa: " + e.getMessage());
		}
		
	}

}
