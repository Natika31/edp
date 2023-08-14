package com.projet.edp.fileTree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.dao.FileContentDAO;
import com.projet.edp.fileTree.domain.FileContent;

@Service
public class FileContentServiceImpl implements FileContentService{

	@Autowired
	FileContentDAO fileContentDAO;

	@Override
	public void save(FileContent fileContent) throws BusinessResourceException {
		fileContentDAO.save(fileContent);		
	}

}
