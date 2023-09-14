package com.projet.edp.contact.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.projet.edp.contact.domain.MyGroup;
import com.projet.edp.contact.domain.MyRecipient;


public class GroupDTOConversion {

	@Autowired
	private ModelMapper modelMapper;

	private RecipientDTOConversion recipientDTOConversion;

	public GroupDTOConversion() {
		super();
		this.modelMapper = new ModelMapper();
		this.recipientDTOConversion = new  RecipientDTOConversion();
	}

	@Bean
	public GroupDTO convertEntityToDTO(MyGroup myGroup) {
		GroupDTO groupDTO = this.modelMapper.map(myGroup, GroupDTO.class);
		List<RecipientDTO> membersDTO = new ArrayList<>();
		for (MyRecipient child : myGroup.getMembers()) {
			RecipientDTO childDTO = recipientDTOConversion.convertEntityToDTO(child);
			membersDTO.add(childDTO);			
		}
		groupDTO.setMembers(membersDTO);		
		return groupDTO;
	}
	
	public List<GroupDTO> convertEntityToDTO(List<MyGroup> groups) {
		List<GroupDTO> groupDTOs = new ArrayList<>();
		for (MyGroup group : groups) {
			GroupDTO groupDTO = convertEntityToDTO(group);
			groupDTOs.add(groupDTO);
		}
		return groupDTOs;
	}

	@Bean
	public MyGroup convertDTOtoEntities(GroupDTO groupDTO) {
		MyGroup myGroup = modelMapper.map(groupDTO, MyGroup.class);
		return myGroup;
	}

}
