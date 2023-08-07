package com.projet.edp.fileTree.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	public byte[] convertInputFileToBinaryArray(String file_origin_path) throws FileNotFoundException, IOException {
		File PDFfile = new File(file_origin_path);
		FileInputStream fileInputStream = new FileInputStream(PDFfile);
		byte[] binaryArray = new byte[(int)PDFfile.length()];
		fileInputStream.read(binaryArray);
		fileInputStream.close();
		return binaryArray;
	}

	@Override
	public void save(FileContent fileContent) throws BusinessResourceException {
		fileContentDAO.save(fileContent);		
	}

}
