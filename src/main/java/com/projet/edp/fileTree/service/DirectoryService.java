package com.projet.edp.fileTree.service;

import java.util.Optional;

import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.domain.Directory;

public interface DirectoryService {
		
	public Optional<Directory> findDirectoryById(Long directory_id) throws BusinessResourceException;
	
	public Directory save(Directory directory) throws BusinessResourceException;
	
	void deleteAll();
	
}
