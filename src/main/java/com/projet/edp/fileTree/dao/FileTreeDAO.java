package com.projet.edp.fileTree.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileTree.domain.TreeItem;

public interface FileTreeDAO extends JpaRepository<TreeItem, Long> {

}
