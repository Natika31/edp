package com.projet.edp.userDirectory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.userDirectory.dao.UserDAO;
import com.projet.edp.userDirectory.domain.MyUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public Optional<MyUser> findUserById(Long user_id) throws BusinessResourceException {
		Optional<MyUser> userFound =  userDAO.findById(user_id);
		if (Boolean.FALSE.equals(userFound.isPresent())) {
			throw new BusinessResourceException("User Not Found", "L'utilisateur avec cet id n'existe pas :" + user_id);
		}
		return userFound;
	}

	@Override
	public MyUser save(MyUser myUser) throws BusinessResourceException {
		return userDAO.save(myUser);
	}

	@Override
	public void deleteAll() {
		userDAO.deleteAll();		
	}

}
