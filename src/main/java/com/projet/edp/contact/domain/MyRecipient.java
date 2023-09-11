package com.projet.edp.contact.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipient")
public class MyRecipient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long item_id;
	
	@Column(name = "name")
	private String name;

	private String item_type;

	public MyRecipient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyRecipient(String name) {
		super();
		this.name = name;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
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

	@Override
	public String toString() {
		return "Recipient [item_id=" + item_id + ", name=" + name + ", item_type=" + item_type + "]";
	}
	
	
}
