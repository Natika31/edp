package com.projet.edp.fileTree.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeItemDTO {
	
	private String item_id;

	private String item_local_path;

	private String item_name;
	
	private List<TreeItemDTO> children;

	private String item_type;

	public TreeItemDTO() {
		super();
		this.setItem_type(item_type);
		this.children = new ArrayList<>();
	}

	public TreeItemDTO(String item_id, String item_local_path, String item_name, String item_type) {
		super();
		this.item_id = item_id;
		this.item_local_path = item_local_path;
		this.item_name = item_name;
		this.children = new ArrayList<>();
		this.setItem_type(item_type);
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
	
	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public List<TreeItemDTO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeItemDTO> children) {
		this.children = children;
	}

}
