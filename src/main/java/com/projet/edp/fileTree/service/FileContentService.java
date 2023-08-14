package com.projet.edp.fileTree.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.domain.FileContent;

public interface FileContentService{
	
	public void save(FileContent fileContent) throws BusinessResourceException;
	
}
