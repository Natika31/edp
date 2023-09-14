package com.projet.edp.contact.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.edp.contact.dao.GroupDAO;
import com.projet.edp.contact.domain.MyGroup;
import com.projet.edp.exceptions.BusinessResourceException;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;

	@Override
	public Optional<MyGroup> findGroupById(Long group_id) throws BusinessResourceException {
		Optional<MyGroup> groupFound =  groupDAO.findById(group_id);
		if (Boolean.FALSE.equals(groupFound.isPresent())) {
			throw new BusinessResourceException("Group Not Found", "Le groupe avec cet id n'existe pas :" + group_id);
		}
		return groupFound;	
	}
	
	@Override
	public List<MyGroup> findAll() {
		return 	groupDAO.findAll();
	}

	@Override
	public MyGroup save(MyGroup myGroup) throws BusinessResourceException {
		return groupDAO.save(myGroup);
	}

	@Override
	public void deleteAll() {
		groupDAO.deleteAll();
	}

}
