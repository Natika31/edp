package com.projet.edp.contact.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.contact.domain.MyUser;

public interface UserDAO extends JpaRepository<MyUser, Long> {

	Optional<MyUser> findUserByName(String name);

}
