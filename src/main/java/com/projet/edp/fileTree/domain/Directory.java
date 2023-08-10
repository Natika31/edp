package com.projet.edp.fileTree.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue(value = "D")
@PrimaryKeyJoinColumn(name = "directory_id")
@Table(name = "directory")
public class Directory extends FileTreeItem implements Serializable {

	@Column(name = "directory_local_path")
	private String directory_local_path;
	
	@Column(name = "directory_name")
	private String directory_name;

	@OneToMany
	private List<FileTreeItem> children;

	public Directory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Directory(String directory_local_path, String directory_name) {
		super();
		this.directory_local_path = directory_local_path;
		this.directory_name = directory_name;
		this.children = new ArrayList<>();
	}
	
	public String getDirectory_local_path() {
		return directory_local_path;
	}

	public void setDirectory_local_path(String directory_local_path) {
		this.directory_local_path = directory_local_path;
	}

	public String getDirectory_name() {
		return directory_name;
	}

	public void setDirectory_name(String directory_name) {
		this.directory_name = directory_name;
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
		return "Directory [item_id=" + super.getItem_id() + ", directory_local_path=" + directory_local_path + ", directory_name="
				+ directory_name + ", children=" + children + "]";
	}

}
