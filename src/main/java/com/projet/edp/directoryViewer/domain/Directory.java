package com.projet.edp.directoryViewer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.projet.edp.fileViewer.domain.MyFile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "directory")
public class Directory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long directory_id;

	@Column(name = "directory_local_path")
	private String directory_local_path;

	@Column(name = "directory_name")
	private String directory_name;

	@OneToMany
	private List<MyFile> children;

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

	public Long getDirectory_id() {
		return directory_id;
	}

	public void setDirectory_id(Long directory_id) {
		this.directory_id = directory_id;
	}

	//TODO: to generate from file name 
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

	public List<MyFile> getChildren() {
		return children;
	}

	public void addChildren(MyFile file) {
		this.children.add(file);
	}

	public void removeChildren(MyFile file) {
		this.children.remove(file);
	}

	public void setChildren(List<MyFile> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Directory [directory_id=" + directory_id + ", directory_path=" + directory_local_path + ", directory_name="
				+ directory_name + ", children=" + children + "]";
	}

}
