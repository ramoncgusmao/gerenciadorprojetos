package com.ramon.gerenciadorprojetos.controller.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ramon.gerenciadorprojetos.domain.model.Projeto;
import com.ramon.gerenciadorprojetos.service.ProjetoService;


@Component
public class StringToProjeto implements Converter<String, Projeto> {

	@Autowired
	private ProjetoService service;
	
	@Override
	public Projeto convert(String source) {
		
		if( source.isEmpty()) {
			return null;
		}
		
		Integer id = Integer.valueOf(source);
		
		return service.findById(id);
	}

}
