package com.projet.edp.fileTree.service;

import java.util.Optional;
import com.projet.edp.exceptions.BusinessResourceException;
import com.projet.edp.fileTree.domain.TreeItem;

public interface FileTreeService{

	public Optional<TreeItem> findFileTreeItemById(Long item_id) throws BusinessResourceException;

	public TreeItem save(TreeItem treeItem) throws BusinessResourceException;
	
	void deleteAll();

}
