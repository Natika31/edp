package com.projet.edp.userDirectory.service;

import java.util.Optional;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.userDirectory.domain.MyUser;

public interface UserService {

	public Optional<MyUser> findUserById(Long user_id) throws BusinessResourceException;
	
	public MyUser save(MyUser myUser) throws BusinessResourceException;
	
	public void deleteAll();
}
