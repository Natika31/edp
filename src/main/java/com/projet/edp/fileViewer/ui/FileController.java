package com.projet.edp.fileViewer.ui;

import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.MyFile;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

	@Autowired
	FileDAO fileDao;
	
	

	public FileController(FileDAO fileRepository) {
		this.fileDao = fileRepository;
	}

	@PostMapping
	public String create(@RequestBody MyFile file) {
		fileDao.save(file);
		return "File is created";
	}
	
	@GetMapping("/api/file")
	public Optional<MyFile>getFileById(@RequestParam Long file_id) {
		Optional<MyFile> file = fileDao.findById(file_id);
		return file;
	}

}
