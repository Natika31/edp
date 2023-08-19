package com.projet.edp.fileTree.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileTree.domain.MyFile;

public interface FileDAO extends JpaRepository<MyFile, Long> {
	

}
