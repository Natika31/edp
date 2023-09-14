package com.projet.edp.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.user.domain.MyUser;

public interface UserDAO extends JpaRepository<MyUser, Long> {

}
