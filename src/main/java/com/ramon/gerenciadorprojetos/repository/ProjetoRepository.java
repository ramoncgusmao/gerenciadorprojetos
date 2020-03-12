package com.ramon.gerenciadorprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.gerenciadorprojetos.domain.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>{

	List<Projeto> findAllByOrderByPrevisaoEntregaAsc();

}
