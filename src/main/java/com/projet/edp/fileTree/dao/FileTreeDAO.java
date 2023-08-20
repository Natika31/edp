package com.projet.edp.fileTree.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileTree.domain.FileTreeItem;

public interface FileTreeDAO extends JpaRepository<FileTreeItem, Long> {

	Optional<FileTreeItem> findByName(String name);

}
