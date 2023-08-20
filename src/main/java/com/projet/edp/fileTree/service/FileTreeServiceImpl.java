package com.projet.edp.fileTree.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.dao.FileTreeDAO;
import com.projet.edp.fileTree.domain.FileTreeItem;

@Service
public class FileTreeServiceImpl implements FileTreeService {

	@Autowired
	FileTreeDAO fileTreeDAO;

	@Override
	public Optional<FileTreeItem> findFileTreeItemById(Long item_id) throws BusinessResourceException {
		Optional<FileTreeItem> itemFound =  fileTreeDAO.findById(item_id);
		if (Boolean.FALSE.equals(itemFound.isPresent())) {
			throw new BusinessResourceException("Item Not Found", "Le fichier ou répertoire avec cet id n'existe pas :" + item_id);
		}
		return itemFound;
	}
	
	@Override
	public Optional<FileTreeItem> findFileTreeItemByName(String item_name) throws BusinessResourceException {
		Optional<FileTreeItem> itemFound =  fileTreeDAO.findByName(item_name);
		if (Boolean.FALSE.equals(itemFound.isPresent())) {
			throw new BusinessResourceException("Item Not Found", "Le fichier ou répertoire avec ce nom n'existe pas :" + item_name);
		}
		return itemFound;
	}

	@Override
	public FileTreeItem save(FileTreeItem fileTreeItem) throws BusinessResourceException {
		return fileTreeDAO.save(fileTreeItem);
	}

	@Override
	public void deleteAll() {
		fileTreeDAO.deleteAll();
	}

}
