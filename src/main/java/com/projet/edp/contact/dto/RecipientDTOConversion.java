package com.projet.edp.contact.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.projet.edp.contact.domain.MyRecipient;

public class RecipientDTOConversion extends RecipientDTO {

	@Autowired
	private ModelMapper modelMapper;

	public RecipientDTOConversion() {
		this.modelMapper = new ModelMapper();
	}
	
	@Bean
	public RecipientDTO convertEntityToDTO(MyRecipient item) {
		RecipientDTO itemDTO = this.modelMapper.map(item, RecipientDTO.class);
		return itemDTO;
	}

	@Bean
	public MyRecipient convertDTOtoEntities(RecipientDTO itemDTO) {
		MyRecipient item = modelMapper.map(itemDTO, MyRecipient.class);
		return item;
	}
}
