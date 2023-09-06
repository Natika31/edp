package com.projet.edp.userDirectory.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.projet.edp.fileTree.dto.DirectoryDTO;
import com.projet.edp.fileTree.dto.DirectoryDTOConversion;
import com.projet.edp.userDirectory.domain.MyUser;

public class UserDTOConversion {

	@Autowired
	private ModelMapper modelMapper;

	private DirectoryDTOConversion directoryDTOConversion;

	public UserDTOConversion() {
		this.modelMapper = new ModelMapper();
		this.directoryDTOConversion = new DirectoryDTOConversion();
	}

	@Bean
	public UserDTO convertEntityToDTO(MyUser user) {
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		DirectoryDTO rootDTO = directoryDTOConversion.convertEntityToDTO(user.getRoot());
		userDTO.setRoot(rootDTO);
		return userDTO;
	}

	@Bean
	public MyUser convertDTOtoEntities(UserDTO userDTO) {
		MyUser user = modelMapper.map(userDTO, MyUser.class);
		user.getRoot().setChildren(directoryDTOConversion.convertChildren(userDTO.getRoot()));
		return user;
	}

}
