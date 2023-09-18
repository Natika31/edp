package com.projet.edp.fileTree.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.projet.edp.accessRight.domain.AccessRight;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

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
	
	private String item_type;
	
	@OneToMany
	private List<AccessRight> accessRights;

	public FileTreeItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileTreeItem(String name, String item_local_path) {
		super();
		this.item_local_path = item_local_path;
		this.name = name;
		this.accessRights = new ArrayList<>();
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

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	
	public List<AccessRight> getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(List<AccessRight> accessRights) {
		this.accessRights = accessRights;
	}
	
	public void addAccessRight(AccessRight accessRight) {
		this.accessRights.add(accessRight);
	}

	public void removeAccessRight(AccessRight accessRight) {
		this.accessRights.remove(accessRight);
	}

	@Override
	public String toString() {
		return "FileTreeItem [item_id=" + item_id + ", name=" + name + ", item_local_path=" + item_local_path
				+ ", item_type=" + item_type + ", accessRights=" + accessRights + "]";
	}

}
