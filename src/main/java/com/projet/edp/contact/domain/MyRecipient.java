package com.projet.edp.contact.domain;

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
@DiscriminatorColumn(name = "recipient_type")
public class MyRecipient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long recipient_id;
	
	@Column(name = "name")
	private String name;

	private String item_type;
	
	@OneToMany
	private List<AccessRight> accessRights;

	public MyRecipient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyRecipient(String name) {
		super();
		this.name = name;
		this.accessRights = new ArrayList<>();
	}

	public Long getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(Long recipient_id) {
		this.recipient_id = recipient_id;
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
		return "MyRecipient [recipient_id=" + recipient_id + ", name=" + name + ", item_type=" + item_type
				+ ", accessRights=" + accessRights + "]";
	}

}
