package com.projet.edp.directoryViewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.edp.directoryViewer.dao.DirectoryDAO;
import com.projet.edp.directoryViewer.domain.Directory;
import com.projet.edp.exceptions.BusinessResourceException;

@Service
public class DirectoryServiceImpl implements DirectoryService {
	
	@Autowired
	DirectoryDAO directoryDAO;

	@Override
	public Optional<Directory> findDirectoryById(Long directory_id) throws BusinessResourceException {
		Optional<Directory> directoryFound =  directoryDAO.findById(directory_id);
		if (Boolean.FALSE.equals(directoryFound.isPresent())) {
            throw new BusinessResourceException("Directory Not Found", "Le répertoire avec cet id n'existe pas :" + directory_id);
        }
		return directoryFound;
	}

	@Override
	public Directory save(Directory directory) throws BusinessResourceException {
		return directoryDAO.save(directory);
	}

	@Override
	public void deleteAll() {
		directoryDAO.deleteAll();
	}

}
