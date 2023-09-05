package com.projet.edp.fileViewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.dao.FileContentDAO;
import com.projet.edp.fileViewer.domain.FileContent;

@Service
public class FileContentServiceImpl implements FileContentService{

	@Autowired
	FileContentDAO fileContentDAO;

	@Override
	public FileContent save(FileContent fileContent) throws BusinessResourceException {
		return fileContentDAO.save(fileContent);		
	}

}
