package com.projet.edp.contact.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.edp.contact.dao.RecipientDAO;
import com.projet.edp.contact.domain.MyRecipient;
import com.projet.edp.exceptions.BusinessResourceException;
@Service
public class RecipientServiceImpl implements RecipientService {

	@Autowired
	RecipientDAO recipientDAO;
	
	@Override
	public Optional<MyRecipient> findRecipientById(Long item_id) throws BusinessResourceException {
		Optional<MyRecipient> itemFound =  recipientDAO.findById(item_id);
		if (Boolean.FALSE.equals(itemFound.isPresent())) {
			throw new BusinessResourceException("Item Not Found", "Le destinataire avec cet id n'existe pas :" + item_id);
		}
		return itemFound;
	}

	@Override
	public MyRecipient save(MyRecipient recipient) throws BusinessResourceException {
		return recipientDAO.save(recipient);
	}

	@Override
	public void deleteAll() {
		recipientDAO.deleteAll();
	}

}
