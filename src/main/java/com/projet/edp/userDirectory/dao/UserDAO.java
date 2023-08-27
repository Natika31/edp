package com.projet.edp.userDirectory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.userDirectory.domain.MyUser;

public interface UserDAO extends JpaRepository<MyUser, Long> {

}
