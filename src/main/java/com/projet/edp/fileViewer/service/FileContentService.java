package com.projet.edp.fileViewer.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileViewer.domain.FileContent;

public interface FileContentService{
	
	public void save(FileContent fileContent) throws BusinessResourceException;
	
	public byte[] convertInputFileToBinaryArray(String file_origin_path) throws FileNotFoundException, IOException;
}
