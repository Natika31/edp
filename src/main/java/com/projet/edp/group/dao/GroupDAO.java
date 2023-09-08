package com.projet.edp.group.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.group.domain.MyGroup;

public interface GroupDAO extends JpaRepository<MyGroup, Long> {

}
