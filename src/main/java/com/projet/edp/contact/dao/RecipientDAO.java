package com.projet.edp.contact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.contact.domain.MyRecipient;

public interface RecipientDAO extends JpaRepository<MyRecipient, Long>{

}
