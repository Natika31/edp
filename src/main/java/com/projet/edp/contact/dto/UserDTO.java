package com.projet.edp.contact.dto;

import com.projet.edp.fileTree.dto.DirectoryDTO;

public class UserDTO {
	
	private String user_id;
	
	private String item_type;

	private String name;
	
	private String mail;
	
	private DirectoryDTO root;

	public UserDTO() {
		super();
		this.root = new DirectoryDTO(null,"home","/home");
	}

	public UserDTO(String user_id, String name, String mail, String item_type) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.mail = mail;
		this.item_type = item_type;
		this.root = new DirectoryDTO(null,"home","/home");
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public DirectoryDTO getRoot() {
		return root;
	}

	public void setRoot(DirectoryDTO rootDTO) {
		this.root = rootDTO;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", item_type=" + item_type + ", name=" + name + ", mail=" + mail
				+ ", root=" + root + "]";
	}
	
	
}
