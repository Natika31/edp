package com.projet.edp.directoryViewer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.directoryViewer.domain.Directory;

public interface DirectoryDAO extends JpaRepository<Directory, Long> {

}
