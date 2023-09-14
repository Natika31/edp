package com.projet.edp.contact.service;

import java.util.List;
import java.util.Optional;

import com.projet.edp.contact.domain.MyGroup;
import com.projet.edp.exceptions.BusinessResourceException;

public interface GroupService {

	public Optional<MyGroup> findGroupById(Long group_id) throws BusinessResourceException;
	
	public List<MyGroup> findAll();
	
	public MyGroup save(MyGroup myGroup) throws BusinessResourceException;
	
	public void deleteAll();

}
