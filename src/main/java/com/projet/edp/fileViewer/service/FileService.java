package com.projet.edp.fileViewer.service;

import java.util.Optional;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileViewer.domain.MyFile;

public interface FileService {

	public Optional<MyFile> findFileById(Long file_id) throws BusinessResourceException;
	
	public void save(MyFile file) throws BusinessResourceException;

	void deleteAll();
	
}
