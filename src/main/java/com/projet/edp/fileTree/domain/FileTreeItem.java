package com.projet.edp.fileTree.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tree_item_type")
public class FileTreeItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long item_id;
	

	@Column(name = "name")
	private String name;

	@Column(name = "item_local_path")
	private String item_local_path;

	public FileTreeItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileTreeItem(String name, String item_local_path) {
		super();
		this.item_local_path = item_local_path;
		this.name = name;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
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

	public void setName(String item_name) {
		this.name = item_name;
	}

	@Override
	public String toString() {
		return "FileTreeItem [item_id=" + item_id + ", item_local_path=" + item_local_path + ", name=" + name
				+ "]";
	}
	
	
	
	
}
