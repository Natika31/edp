package com.projet.edp.fileTree.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.edp.fileTree.domain.Directory;

public interface DirectoryDAO extends JpaRepository<Directory, Long> {

}
