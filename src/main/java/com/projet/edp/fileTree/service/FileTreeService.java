package com.projet.edp.fileTree.service;

import java.util.Optional;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.domain.FileTreeItem;

public interface FileTreeService{

	public Optional<FileTreeItem> findFileTreeItemById(Long item_id) throws BusinessResourceException;
	
	public Optional<FileTreeItem> findFileTreeItemByName(String item_name) throws BusinessResourceException;

	public FileTreeItem save(FileTreeItem treeItem) throws BusinessResourceException;
	
	void deleteAll();


}
