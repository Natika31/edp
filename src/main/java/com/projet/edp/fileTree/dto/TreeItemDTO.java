package com.projet.edp.fileTree.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeItemDTO {
		
	private String item_id;
	
	private String item_type;

	private String name;

	private String item_local_path;

	private List<TreeItemDTO> children;

	public TreeItemDTO() {
		super();
		this.children = new ArrayList<>();
	}

	public TreeItemDTO(String item_id, String name, String item_local_path) {
		super();
		this.item_id = item_id;
		this.item_local_path = item_local_path;
		this.name = name;
		this.children = new ArrayList<>();
	}

	public String getItem_id() {
		return item_id;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeItemDTO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeItemDTO> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TreeItemDTO [item_id=" + item_id + ", item_type=" + item_type + ", name=" + name + ", item_local_path="
				+ item_local_path + ", children=" + children + "]";
	}

}
