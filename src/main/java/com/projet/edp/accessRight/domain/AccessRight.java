package com.projet.edp.accessRight.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "access_right")
public class AccessRight implements Serializable {
	
	@EmbeddedId
	private AccessRightKey id;
	
//	@Column(name = "creation_date")
//	Date creation_date;
	
	@Column(name = "sendeur")
	Long sendeurId;

	public AccessRight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessRight(AccessRightKey id, Long sendeurId) {
		super();
		this.id = id;
		this.sendeurId = sendeurId;
	}
	
	public AccessRightKey getId() {
		return id;
	}

	public void setId(AccessRightKey id) {
		this.id = id;
	}

	public Long getSendeurId() {
		return sendeurId;
	}

	public void setSendeur(Long sendeurId) {
		this.sendeurId = sendeurId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, sendeurId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessRight other = (AccessRight) obj;
		return Objects.equals(id, other.id) && Objects.equals(sendeurId, other.sendeurId);
	}

	@Override
	public String toString() {
		return "AccessRight [id=" + id + ", sendeurId=" + sendeurId + "]";
	}
	
	
	
}
