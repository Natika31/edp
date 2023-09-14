package com.projet.edp.group.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.projet.edp.user.domain.MyUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MY_GROUP")
public class MyGroup implements Serializable {
	
	private static final String GROUP_TYPE = "group";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long group_id;
	
	private String item_type;

	@Column(name = "name")
	private String name;

	@OneToMany
	private List<MyUser> members;

	public MyGroup() {
		super();
		this.members = new ArrayList<>();
		this.setItem_type(GROUP_TYPE);
	}

	public MyGroup(String name) {
		super();
		this.name = name;
		this.members = new ArrayList<>();
		this.setItem_type(GROUP_TYPE);
	}

	public Long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public List<MyUser> getMembers() {
		return members;
	}

	public void setMembers(List<MyUser> members) {
		this.members = members;
	}


	public void addMember(MyUser user) {
		this.members.add(user);
	}

	public void removeMember(MyUser user) {
		this.members.remove(user);
	}

	@Override
	public String toString() {
		return "Group [group_id=" + group_id + ", name=" + name + ", item_type=" + item_type + ", members=" + members
				+ "]";
	}	
}