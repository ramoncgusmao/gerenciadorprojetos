package com.ramon.gerenciadorprojetos.controller.conversor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ConvertStringToDate  implements Converter<String, Date> {


	
	@Override
	public Date convert(String source) {
		
		if( source.isEmpty()) {
			return null;
		}
		
		Date data;
		try {
			data = new SimpleDateFormat("yyyy-MM-dd").parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return data;
	}



}
