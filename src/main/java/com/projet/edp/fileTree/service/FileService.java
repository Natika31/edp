package com.projet.edp.fileTree.service;

import java.util.Optional;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.domain.MyFile;

public interface FileService {

	public Optional<MyFile> findFileById(Long file_id) throws BusinessResourceException;
	
	public MyFile save(MyFile file) throws BusinessResourceException;

	void deleteAll();
	
}
