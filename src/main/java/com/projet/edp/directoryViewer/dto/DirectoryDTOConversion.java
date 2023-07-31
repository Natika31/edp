package com.projet.edp.directoryViewer.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.projet.edp.directoryViewer.domain.Directory;


public class DirectoryDTOConversion {
	
	@Autowired
	private ModelMapper modelMapper;

	public DirectoryDTOConversion() {
		this.modelMapper = new ModelMapper();
	}

	@Bean
	public DirectoryDTO convertEntityToDTO(Directory directory) {
		DirectoryDTO directoryDTO = this.modelMapper.map(directory, DirectoryDTO.class);
		return directoryDTO;
	}
	
	@Bean
	public Directory convertDTOtoEntities(DirectoryDTO directoryDTO) {
		Directory directory = modelMapper.map(directoryDTO, Directory.class);
		return directory;
	}
}
