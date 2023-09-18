package com.projet.edp.accessRight.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.accessRight.domain.AccessRight;
import com.projet.edp.accessRight.domain.AccessRightKey;

public interface AccessRightDAO extends JpaRepository<AccessRight, AccessRightKey> {

}
