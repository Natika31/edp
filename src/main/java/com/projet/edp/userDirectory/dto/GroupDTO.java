package com.projet.edp.userDirectory.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupDTO {
	
	public static final String GROUP_TYPE = "group";
	
	private String group_id;

	private String name;

	private String item_type;

	private List<UserDTO> members;

	public GroupDTO() {
		super();
		this.item_type = GROUP_TYPE;
		this.members = new ArrayList<>();
	}

	public GroupDTO(String group_id, String name) {
		super();
		this.group_id = group_id;
		this.name = name;
		this.item_type = GROUP_TYPE;
		this.members = new ArrayList<>();
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
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

	public List<UserDTO> getMembers() {
		return members;
	}

	public void setMembers(List<UserDTO> members) {
		this.members = members;
	}
	
	public void addMember(UserDTO user) {
		this.members.add(user);
	}

	public void removeMember(UserDTO user) {
		this.members.remove(user);
	}

	@Override
	public String toString() {
		return "GroupDTO [group_id=" + group_id + ", name=" + name + ", item_type=" + item_type + ", members=" + members
				+ "]";
	}

}
