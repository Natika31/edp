package com.projet.edp.fileTree.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileViewer.domain.FileContent;

public interface FileContentDAO extends JpaRepository<FileContent, Long> {
	

}
