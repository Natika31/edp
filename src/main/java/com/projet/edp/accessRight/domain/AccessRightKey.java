package com.projet.edp.accessRight.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccessRightKey implements Serializable {

	@Column(name = "recipient_id")
	private Long recipient_id;

	@Column(name = "item_id")
	private Long item_id;

	public AccessRightKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessRightKey(Long recipient_id, Long item_id) {
		super();
		this.recipient_id = recipient_id;
		this.item_id = item_id;
	}

	public Long getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(Long recipient_id) {
		this.recipient_id = recipient_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(item_id, recipient_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessRightKey other = (AccessRightKey) obj;
		return Objects.equals(item_id, other.item_id) && Objects.equals(recipient_id, other.recipient_id);
	}

	@Override
	public String toString() {
		return "AccessRightKey [recipient_id=" + recipient_id + ", item_id=" + item_id + "]";
	}
	
	
	
	
}
