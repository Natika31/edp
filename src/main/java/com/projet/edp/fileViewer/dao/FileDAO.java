package com.projet.edp.fileViewer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileViewer.domain.File;

public interface FileDAO extends JpaRepository<File, Long> {
	

}
