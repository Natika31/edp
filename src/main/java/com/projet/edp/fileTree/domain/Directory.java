package com.projet.edp.fileTree.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue(value = "DIRECTORY")
@PrimaryKeyJoinColumn(name = "directory_id")
@Table(name = "directory")
public class Directory extends TreeItem implements Serializable {

	@OneToMany
	private List<TreeItem> children;

	private String item_type;
	

	public Directory() {
		super();
		this.children = new ArrayList<>();
		this.setItem_type(this.getClass().toString());
	}

	public Directory(String item_name, String item_local_path) {
		super(item_name,item_local_path);
		this.children = new ArrayList<>();
		this.setItem_type(this.getClass().toString());
	}
	
	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	
	public List<TreeItem> getChildren() {
		return children;
	}

	public void addChildren(TreeItem item) {
		this.children.add(item);
	}

	public void removeChildren(TreeItem item) {
		this.children.remove(item);
	}

	public void setChildren(List<TreeItem> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Directory [item_id=" + super.getItem_id() + ", directory_local_path=" + super.getItem_local_path() + ", directory_name="
				+ super.getItem_name() + ", item_type=" + item_type + ", children=" + children + "]";
	}

}
