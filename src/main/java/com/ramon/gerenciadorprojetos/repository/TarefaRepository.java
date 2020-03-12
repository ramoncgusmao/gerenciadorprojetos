package com.ramon.gerenciadorprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.gerenciadorprojetos.domain.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{

	List<Tarefa> findByProjeto_Id(Integer projetoId);

	boolean existsByProjeto_Id(Integer id);

}
