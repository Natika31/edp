package com.projet.edp.contact.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue(value = "GROUP")
@PrimaryKeyJoinColumn(name = "group_id")
@Table(name = "my_group")
public class MyGroup extends MyRecipient implements Serializable {
	
	private static final String GROUP_TYPE = "group";
	
	private String item_type;

	@OneToMany
	private List<MyRecipient> members;

	public MyGroup() {
		super();
		this.members = new ArrayList<>();
		this.setItem_type(GROUP_TYPE);
	}

	public MyGroup(String name) {
		super(name);
		this.members = new ArrayList<>();
		this.setItem_type(GROUP_TYPE);
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	
	public void addMember(MyRecipient recipient) {
		this.members.add(recipient);
	}

	public void removeMember(MyRecipient recipient) {
		this.members.remove(recipient);
	}

	public List<MyRecipient> getMembers() {
		return members;
	}

	public void setMembers(List<MyRecipient> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "MyGroup [" + super.toString() + ", item_type=" + item_type + ", members=" + members + "]";
	}


}