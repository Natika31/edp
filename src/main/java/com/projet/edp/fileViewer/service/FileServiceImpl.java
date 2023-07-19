package com.projet.edp.fileViewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.MyFile;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	FileDAO fileDAO;

	@Override
	public Optional<MyFile> findFileById(Long file_id)throws BusinessResourceException{
		Optional<MyFile> itemFound =  fileDAO.findById(file_id);
		if (Boolean.FALSE.equals(itemFound.isPresent())) {
            throw new BusinessResourceException("Item Not Found", "Le fichier avec cet id n'existe pas :" + file_id);
        }
		return itemFound;
	}

	@Override
	public void save(MyFile file) throws BusinessResourceException{
		fileDAO.save(file);	
	}

}
