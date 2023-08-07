package com.projet.edp.fileTree.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileTree.domain.FileContent;

public interface FileContentDAO extends JpaRepository<FileContent, Long> {
	

}
