package com.projet.edp.fileTree.dto;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;


public class DirectoryDTOConversion {
	
	@Autowired
	private ModelMapper modelMapper;
	
	private TreeDTOConversion treeDTOConversion;

	public DirectoryDTOConversion() {
		this.modelMapper = new ModelMapper();
		this.treeDTOConversion = new TreeDTOConversion();
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
	
	@Bean
	public List<FileTreeItem> convertChildren(DirectoryDTO directoryDTO) {
		List<FileTreeItem> newChildren = new ArrayList<>();
		for (TreeItemDTO childDTO : directoryDTO.getChildren()) {
			if (childDTO.getItem_type().equals(TreeDTOConversion.FILE_TYPE)) {
				FileTreeItem childItem = treeDTOConversion.convertDTOtoEntities(childDTO);
				newChildren.add(childItem );
			}else if (childDTO.getItem_type().equals(TreeDTOConversion.DIRECTORY_TYPE)) {
				FileTreeItem childItem = treeDTOConversion.convertDirectoryItemDTOtoDirectoryItem(childDTO);
				newChildren.add(childItem );
			}			
		}
		return newChildren;
	}
}
