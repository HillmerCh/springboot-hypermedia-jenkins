package org.hillmerch.player.web.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperProducer {

	private ModelMapper modelMapper;

	public MapperProducer() {
		this.modelMapper = new ModelMapper();
	}
	@Bean
	public ModelMapper get() {
		return modelMapper;
	}

}
