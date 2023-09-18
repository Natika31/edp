package com.projet.edp.contact.dto;

import java.util.ArrayList;
import java.util.List;

import com.projet.edp.accessRight.domain.AccessRight;

public class GroupDTO {
		
	private String group_id;

	private String name;

	private String item_type;

	private List<RecipientDTO> members;
	
	private List<AccessRight> accessRights;


	public GroupDTO() {
		super();
		this.members = new ArrayList<>();
		this.accessRights = new ArrayList<>();
	}

	public GroupDTO(String group_id, String name) {
		super();
		this.group_id = group_id;
		this.name = name;
		this.members = new ArrayList<>();
		this.accessRights = new ArrayList<>();
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
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

	public List<RecipientDTO> getMembers() {
		return members;
	}

	public void setMembers(List<RecipientDTO> membersDTO) {
		this.members = membersDTO;
	}
	
	public void addMember(RecipientDTO user) {
		this.members.add(user);
	}

	public void removeMember(RecipientDTO user) {
		this.members.remove(user);
	}
	
	public List<AccessRight> getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(List<AccessRight> accessRights) {
		this.accessRights = accessRights;
	}

	@Override
	public String toString() {
		return "GroupDTO [group_id=" + group_id + ", name=" + name + ", item_type=" + item_type + ", members=" + members
				+ ", accessRights=" + accessRights + "]";
	}

}
