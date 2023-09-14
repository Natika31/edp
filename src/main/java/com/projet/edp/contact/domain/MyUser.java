package com.projet.edp.user.domain;

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
@Table(name = "my_user")
public class MyUser implements Serializable {
	
	private static final String USER_TYPE = "user";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
	private String item_type;

	@Column(name = "name")
	private String name;
	
	@Column(name = "mail")
	private String mail;
	
	@OneToOne
	@JoinColumn(name = "root_directory_fk", nullable=false)
	private Directory root;

	public MyUser() {
		super();
		this.setItem_type(USER_TYPE);
	}

	public MyUser(String name, String mail,Directory root) {
		super();
		this.name = name;
		this.mail = mail;
		this.root = root;
		this.setItem_type(USER_TYPE);
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
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

	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return "MyUser [user_id=" + user_id + ", item_type=" + item_type + ", name=" + name + ", mail=" + mail
				+ ", root=" + root + "]";
	}	
}
