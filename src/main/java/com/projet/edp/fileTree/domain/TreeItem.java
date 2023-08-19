package com.projet.edp.fileTree.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "file_tree_item")
public class FileTreeItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long item_id;

	@Column(name = "item_local_path")
	private String item_local_path;

	@Column(name = "item_name")
	private String item_name;

	public FileTreeItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileTreeItem(String item_local_path, String item_name) {
		super();
		this.item_local_path = item_local_path;
		this.item_name = item_name;
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

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	@Override
	public String toString() {
		return "FileTreeItem [item_id=" + item_id + ", item_local_path=" + item_local_path + ", item_name=" + item_name
				+ "]";
	}
	
	
	
	
}
