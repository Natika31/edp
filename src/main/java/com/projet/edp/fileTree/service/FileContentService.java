package com.projet.edp.fileTree.service;

import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.domain.FileContent;

public interface FileContentService{
	
	public FileContent save(FileContent fileContent) throws BusinessResourceException;
	
}
