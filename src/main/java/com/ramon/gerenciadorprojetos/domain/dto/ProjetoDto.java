package com.ramon.gerenciadorprojetos.domain.dto;

import com.ramon.gerenciadorprojetos.controller.conversor.ConvertStringToDate;
import com.ramon.gerenciadorprojetos.domain.model.Projeto;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class ProjetoDto {

	@NotNull
	private String titulo;
	
	@NotNull
	private String previsaoEntrega;

	public Projeto convertToEntity() {
		// TODO Auto-generated method stub
		ConvertStringToDate convert = new ConvertStringToDate();
		return Projeto.builder().titulo(titulo)
				.previsaoEntrega( convert.convert(previsaoEntrega)).build();
	}
}
