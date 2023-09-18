package com.projet.edp.accessRight.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.edp.accessRight.dao.AccessRightDAO;
import com.projet.edp.accessRight.domain.AccessRight;
import com.projet.edp.accessRight.domain.AccessRightKey;
import com.projet.edp.exceptions.BusinessResourceException;

@Service
public class AccessRightServiceImpl implements AccessRightService {

	@Autowired
	private AccessRightDAO accessRightDAO;

	@Override
	public Optional<AccessRight> findAccessRightById(AccessRightKey id) throws BusinessResourceException {
		Optional<AccessRight> accessRightFound =  accessRightDAO.findById(id);
		if (Boolean.FALSE.equals(accessRightFound.isPresent())) {
			throw new BusinessResourceException("Access right Not Found", "Le droit d'accès avec cet id n'existe pas :" + id);
		}
		return accessRightFound;
	}

	@Override
	public List<AccessRight> findAll() {
		return accessRightDAO.findAll();
	}

	@Override
	public AccessRight save(AccessRight access_right) throws BusinessResourceException {
		return accessRightDAO.save(access_right);
	}

	@Override
	public void deleteAll() {
		accessRightDAO.deleteAll();
	}

}
