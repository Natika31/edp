package com.projet.edp.contact.dto;

import java.util.ArrayList;
import java.util.List;

public class RecipientDTO {

	private String item_id;
	
	private String item_type;

	private String name;

	private List<RecipientDTO> children;

	public RecipientDTO() {
		super();
		this.children = new ArrayList<>();
	}

	public RecipientDTO(String item_id, String name) {
		super();
		this.item_id = item_id;
		this.name = name;
		this.children = new ArrayList<>();
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RecipientDTO> getChildren() {
		return children;
	}

	public void setChildren(List<RecipientDTO> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "RecipientDTO [item_id=" + item_id + ", item_type=" + item_type + ", name=" + name + ", children="
				+ children + "]";
	}
	
}
