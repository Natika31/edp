package com.projet.edp.accessRight.service;

import java.util.List;
import java.util.Optional;

import com.projet.edp.accessRight.domain.AccessRight;
import com.projet.edp.accessRight.domain.AccessRightKey;
import com.projet.edp.exceptions.BusinessResourceException;

public interface AccessRightService {
	
	public Optional<AccessRight> findAccessRightById(AccessRightKey id) throws BusinessResourceException;
	
	public List<AccessRight> findAll();

	public AccessRight save(AccessRight access_right) throws BusinessResourceException;
	
	void deleteAll();

}
