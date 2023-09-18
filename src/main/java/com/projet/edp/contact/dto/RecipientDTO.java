package com.projet.edp.contact.dto;

import java.util.ArrayList;
import java.util.List;

import com.projet.edp.accessRight.domain.AccessRight;

public class RecipientDTO {

	private String recipient_id;
	
	private String name;
	
	private String item_type;

	private List<RecipientDTO> members;
	
	private List<AccessRight> accessRights;

	public RecipientDTO() {
		super();
		this.members = new ArrayList<>();
		this.accessRights = new ArrayList<>();
	}

	public RecipientDTO(String recipient_id, String name) {
		super();
		this.recipient_id = recipient_id;
		this.name = name;
		this.members = new ArrayList<>();
		this.accessRights = new ArrayList<>();

	}

	public String getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(String recipient) {
		this.recipient_id = recipient;
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

	public List<RecipientDTO> getMembers() {
		return members;
	}

	public void setMembers(List<RecipientDTO> members) {
		this.members = members;
	}

	public List<AccessRight> getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(List<AccessRight> accessRights) {
		this.accessRights = accessRights;
	}

	@Override
	public String toString() {
		return "RecipientDTO [recipient_id=" + recipient_id + ", name=" + name + ", item_type=" + item_type
				+ ", members=" + members + ", accessRights=" + accessRights + "]";
	}


	
}
