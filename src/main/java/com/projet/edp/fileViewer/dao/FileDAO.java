package com.projet.edp.fileViewer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileViewer.domain.MyFile;

public interface FileDAO extends JpaRepository<MyFile, Long> {
	

}
