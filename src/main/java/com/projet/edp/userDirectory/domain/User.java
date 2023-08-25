package com.projet.edp.userDirectory.domain;

import java.io.Serializable;
import com.projet.edp.fileTree.domain.Directory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "mail")
	private String mail;
	
	private String item_type;
	
	@OneToOne
	@JoinColumn(name = "root_directory_fk", nullable=false)
	private Directory root;

	public User() {
		super();
		this.root = new Directory();
		this.setItem_type(this.getClass().toString());
	}

	public User(String name, String mail) {
		super();
		this.name = name;
		this.mail = mail;
		this.root = new Directory("home","/home");
		this.setItem_type(this.getClass().toString());
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
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

	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", mail=" + mail + ", item_type=" + item_type + ", root="
				+ root + "]";
	}	
}
