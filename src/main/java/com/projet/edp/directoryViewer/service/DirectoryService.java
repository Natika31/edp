package com.projet.edp.directoryViewer.service;

import java.util.Optional;
import com.projet.edp.directoryViewer.domain.Directory;
import com.projet.edp.exceptions.BusinessResourceException;

public interface DirectoryService {
		
	public Optional<Directory> findDirectoryById(Long directory_id) throws BusinessResourceException;
	
	public Directory save(Directory directory) throws BusinessResourceException;
	
	void deleteAll();
	
}
