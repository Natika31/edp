package com.projet.edp.fileTree.dto;

import java.util.ArrayList;
import java.util.List;

public class DirectoryDTO
{

	private String item_id;

	private String item_local_path;

	private String item_name;

	private List<TreeItemDTO> children;

	public DirectoryDTO() {
		super();
		this.children = new ArrayList<TreeItemDTO>();
	}

	public DirectoryDTO(String directory_id, String directory_local_path, String directory_name) {
		super();
		this.item_id = directory_id;
		this.item_local_path = directory_local_path;
		this.item_name = directory_name;
		this.children = new ArrayList<TreeItemDTO>();
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_local_path() {
		return item_local_path;
	}

	public void setItem_local_path(String item_local_path) {
		this.item_local_path = item_local_path;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public List<TreeItemDTO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeItemDTO> children) {
		this.children = children;
	}

	public void addChildrenDTO(TreeItemDTO itemDTO) {
		this.children.add(itemDTO);		
	}

	public void removeChildrenDTO(TreeItemDTO itemDTO) {
		this.children.remove(itemDTO);		
	}

}
