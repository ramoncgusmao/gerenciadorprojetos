package com.ramon.gerenciadorprojetos.domain.dto;

import java.util.Date;

import com.ramon.gerenciadorprojetos.domain.model.Projeto;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class ProjetoDto {

	@NotNull
	private String titulo;
	
	@NotNull
	private Date previsaoEntrega;

	public Projeto convertToEntity() {
		// TODO Auto-generated method stub
		return Projeto.builder().titulo(titulo).previsaoEntrega(previsaoEntrega).build();
	}
}
