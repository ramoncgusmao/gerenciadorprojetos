package com.ramon.gerenciadorprojetos.domain.dto;

import com.ramon.gerenciadorprojetos.domain.model.Projeto;
import com.ramon.gerenciadorprojetos.domain.model.Tarefa;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDto {

	@NotNull
	private Integer id; 
	
	@NotNull
	private String titulo; 
	
	@NotNull
	private Integer projeto;

	public Tarefa convertToEntity() {
		// TODO Auto-generated method stub
		return Tarefa.builder()
				.titulo(titulo)
				.projeto(Projeto.builder().id(projeto).build())
				.build();
	}
	
	
}
