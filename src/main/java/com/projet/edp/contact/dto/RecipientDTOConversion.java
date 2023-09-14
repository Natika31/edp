package com.projet.edp.contact.dto;

import java.util.ArrayList;
import java.util.List;

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
		itemDTO.setItem_type(item.getItem_type());
		return itemDTO;
	}

	@Bean
	public MyRecipient convertDTOtoEntities(RecipientDTO itemDTO) {
		MyRecipient item = modelMapper.map(itemDTO, MyRecipient.class);
		return item;
	}

	public List<RecipientDTO> convertEntityToDTO(List<MyRecipient> recipients) {
		List<RecipientDTO> recipientDTOs = new ArrayList<>();
		for (MyRecipient recipient : recipients) {
			RecipientDTO recipientDTO = convertEntityToDTO(recipient);
			recipientDTOs.add(recipientDTO);
		}
		return recipientDTOs;
	}
}
