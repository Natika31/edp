package com.projet.edp.fileTree.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileTree.domain.FileTreeItem;

public interface FileTreeDAO extends JpaRepository<FileTreeItem, Long> {

}
