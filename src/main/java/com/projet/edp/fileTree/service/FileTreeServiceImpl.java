package com.projet.edp.fileTree.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.dao.FileTreeDAO;
import com.projet.edp.fileTree.domain.TreeItem;

@Service
public class FileTreeServiceImpl implements FileTreeService {

	@Autowired
	FileTreeDAO fileTreeDAO;

	@Override
	public Optional<TreeItem> findFileTreeItemById(Long item_id) throws BusinessResourceException {
		Optional<TreeItem> itemFound =  fileTreeDAO.findById(item_id);
		if (Boolean.FALSE.equals(itemFound.isPresent())) {
			throw new BusinessResourceException("Item Not Found", "Le fichier ou répertoire avec cet id n'existe pas :" + item_id);
		}
		return itemFound;
	}

	@Override
	public TreeItem save(TreeItem treeItem) throws BusinessResourceException {
		return fileTreeDAO.save(treeItem);
	}

	@Override
	public void deleteAll() {
		fileTreeDAO.deleteAll();
	}

}
