package com.projet.edp.contact.service;

import java.util.Optional;

import com.projet.edp.contact.domain.MyRecipient;
import com.projet.edp.exceptions.BusinessResourceException;

public interface RecipientService {

	public Optional<MyRecipient> findRecipientById(Long item_id) throws BusinessResourceException;
	
	public MyRecipient save(MyRecipient recipient) throws BusinessResourceException;
	
	void deleteAll();
}
