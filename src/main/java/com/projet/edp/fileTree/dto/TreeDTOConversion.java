package com.projet.edp.fileTree.dto;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.TreeItem;
import com.projet.edp.fileTree.domain.MyFile;

public class TreeDTOConversion {

	@Autowired
	private ModelMapper modelMapper;

	public TreeDTOConversion() {
		this.modelMapper = new ModelMapper();
	}

	@Bean
	public TreeItemDTO convertEntityToDTO(TreeItem item) {
		TreeItemDTO itemDTO = this.modelMapper.map(item, TreeItemDTO.class);
		return itemDTO;
	}

	@Bean
	public TreeItem convertDTOtoEntities(TreeItemDTO itemDTO) {
		TreeItem item = modelMapper.map(itemDTO, TreeItem.class);
		return item;
	}

	@Bean
	public TreeItem convertFileItemDTOtoFileItem(TreeItemDTO fItemDTO) {
		TreeItem item = modelMapper.map(fItemDTO, MyFile.class);
		return item;
	}

	@Bean
	public TreeItem convertDirectoryItemDTOtoDirectoryItem(TreeItemDTO dItemDTO) {
		if(dItemDTO.getChildren().size() > 0) {
			Directory item = modelMapper.map(dItemDTO, Directory.class);
			List<TreeItem> newChildren = new ArrayList<>();
			for (TreeItemDTO childDTO : dItemDTO.getChildren()) {
				if (childDTO.getItem_type().equals(MyFile.class.toString())) {
					TreeItem childItem = convertFileItemDTOtoFileItem(childDTO);
					newChildren.add(childItem );
				}else if (childDTO.getItem_type().equals(Directory.class.toString())) {
					TreeItem childItem = convertDirectoryItemDTOtoDirectoryItem(childDTO);
					newChildren.add(childItem );
				}			
			}
			item.setChildren(newChildren);
			return item;
		}
		return modelMapper.map(dItemDTO, Directory.class);
	}

}
