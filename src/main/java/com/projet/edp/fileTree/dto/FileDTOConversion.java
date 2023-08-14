package com.projet.edp.fileTree.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.projet.edp.fileTree.domain.MyFile;

public class FileDTOConversion {
	
	@Autowired
	private ModelMapper modelMapper;

	public FileDTOConversion() {
		this.modelMapper = new ModelMapper();
	}

	@Bean
	public FileDTO convertEntityToDTO(MyFile file) {
		FileDTO fileDTO = this.modelMapper.map(file, FileDTO.class);
		return fileDTO;
	}
	
	@Bean
	public MyFile convertDTOtoEntities(FileDTO fileDTO) {
		MyFile file = modelMapper.map(fileDTO, MyFile.class);
		return file;
	}	
	
}
