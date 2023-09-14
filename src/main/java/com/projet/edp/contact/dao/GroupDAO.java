package com.projet.edp.contact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.contact.domain.MyGroup;

public interface GroupDAO extends JpaRepository<MyGroup, Long> {

}
