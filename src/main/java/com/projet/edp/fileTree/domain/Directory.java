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
public class Directory extends FileTreeItem implements Serializable {

	public static final String DIRECTORY_TYPE = "folder";

	@OneToMany
	private List<FileTreeItem> children;

	private String item_type;


	public Directory() {
		super();
		this.children = new ArrayList<>();
		this.item_type = DIRECTORY_TYPE;
	}

	public Directory(String item_name, String item_local_path) {
		super(item_name,item_local_path);
		this.children = new ArrayList<>();
		this.item_type = DIRECTORY_TYPE;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}


	public List<FileTreeItem> getChildren() {
		return children;
	}

	public void addChildren(FileTreeItem item) {
		this.children.add(item);
	}

	public void removeChildren(FileTreeItem item) {
		this.children.remove(item);
	}

	public void setChildren(List<FileTreeItem> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Directory [" + super.toString() + ", item_type=" + item_type + ", children=" + children + "]";
	}

}
