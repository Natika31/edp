package com.projet.edp.group.service;

import java.util.List;
import java.util.Optional;

import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.group.domain.MyGroup;

public interface GroupService {

	public Optional<MyGroup> findGroupById(Long group_id) throws BusinessResourceException;
	
	public List<MyGroup> findAll();
	
	public MyGroup save(MyGroup myGroup) throws BusinessResourceException;
	
	public void deleteAll();

}
