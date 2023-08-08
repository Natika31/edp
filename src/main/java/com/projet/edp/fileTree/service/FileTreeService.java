package com.projet.edp.fileTree.service;

import java.util.Optional;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.domain.FileTreeItem;

public interface FileTreeService{

	public Optional<FileTreeItem> findFileTreeItemById(Long item_id) throws BusinessResourceException;

	public FileTreeItem save(FileTreeItem fileTreeItem) throws BusinessResourceException;
	
	void deleteAll();

}
