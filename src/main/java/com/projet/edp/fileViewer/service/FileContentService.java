package com.projet.edp.fileViewer.service;

import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileViewer.domain.FileContent;

public interface FileContentService{
	
	public FileContent save(FileContent fileContent) throws BusinessResourceException;
	
}
