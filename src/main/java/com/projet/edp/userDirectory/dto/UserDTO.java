package com.projet.edp.userDirectory.dto;

import com.projet.edp.fileTree.dto.DirectoryDTO;

public class UserDTO {
	
	public static final String MEMBER_TYPE = "user";
	
	private String user_id;

	private String name;
	
	private String mail;
	
	private String item_type;
	
	private DirectoryDTO root;

	public UserDTO() {
		super();
		this.item_type = MEMBER_TYPE;
	}

	public UserDTO(String user_id, String name, String mail) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.mail = mail;
		this.item_type = MEMBER_TYPE;

	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getItem_type() {
		return item_type;
	}

	public DirectoryDTO getRoot() {
		return root;
	}

	public void setRoot(DirectoryDTO rootDTO) {
		this.root = rootDTO;
	}
}
