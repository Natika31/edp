package com.projet.edp.fileTree.dto;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.domain.MyFile;

public class TreeDTOConversion {

	private static final String DIRECTORY_TYPE = "folder";

	private static final String FILE_TYPE = "file";

	@Autowired
	private ModelMapper modelMapper;

	public TreeDTOConversion() {
		this.modelMapper = new ModelMapper();
	}

	@Bean
	public TreeItemDTO convertEntityToDTO(FileTreeItem item) {
		TreeItemDTO itemDTO = this.modelMapper.map(item, TreeItemDTO.class);
		renameItemType(itemDTO);			
		return itemDTO;
	}

	@Bean
	public FileTreeItem convertDTOtoEntities(TreeItemDTO itemDTO) {
		FileTreeItem item = modelMapper.map(itemDTO, FileTreeItem.class);
		return item;
	}

	@Bean
	public FileTreeItem convertFileItemDTOtoFileItem(TreeItemDTO fItemDTO) {
		FileTreeItem item = modelMapper.map(fItemDTO, MyFile.class);
		return item;
	}

	@Bean
	public FileTreeItem convertDirectoryItemDTOtoDirectoryItem(TreeItemDTO dItemDTO) {
		Directory item = modelMapper.map(dItemDTO, Directory.class);
		if(!dItemDTO.getChildren().isEmpty()) 		
			item.setChildren(convertChildren(dItemDTO));
		return item;
	}

	private List<FileTreeItem> convertChildren(TreeItemDTO dItemDTO) {
		List<FileTreeItem> newChildren = new ArrayList<>();
		for (TreeItemDTO childDTO : dItemDTO.getChildren()) {
			if (childDTO.getItem_type().equals(FILE_TYPE)) {
				FileTreeItem childItem = convertFileItemDTOtoFileItem(childDTO);
				newChildren.add(childItem );
			}else if (childDTO.getItem_type().equals(DIRECTORY_TYPE)) {
				FileTreeItem childItem = convertDirectoryItemDTOtoDirectoryItem(childDTO);
				newChildren.add(childItem );
			}			
		}
		return newChildren;
	}
	
	private void renameItemType(TreeItemDTO itemDTO) {
		if (itemDTO.getItem_type().equals(MyFile.class.toString())) {
			itemDTO.setItem_type(FILE_TYPE); 
		}else if (itemDTO.getItem_type().equals(Directory.class.toString())) {
			itemDTO.setItem_type(DIRECTORY_TYPE);
			for (TreeItemDTO childDTO : itemDTO.getChildren()) {
				renameItemType(childDTO);
			}
		}
	}

}
