package com.projet.edp.contact.domain;

import java.io.Serializable;

import com.projet.edp.fileTree.domain.Directory;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue(value = "USER")
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "my_user")
public class MyUser extends MyRecipient implements Serializable {
	
	private static final String USER_TYPE = "user";
	
	private String item_type;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "mail")
	private String mail;
	
	@OneToOne
	@JoinColumn(name = "root_directory_fk", nullable=false)
	private Directory root;

	public MyUser() {
		super();
		this.setItem_type(USER_TYPE);
	}

	public MyUser(String name, String login, String password, String mail, Directory root) {
		super(name);
		this.login = login;
		this.password = password;
		this.mail = mail;
		this.root = root;
		this.setItem_type(USER_TYPE);
	}
	
	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return "MyUser [" + super.toString() + "item_type=" + item_type + ", login=" + login + ", password=" + password + ", mail=" + mail
				+ ", root=" + root + "]";
	}
}
