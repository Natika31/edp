package com.projet.edp.fileViewer.ui;

import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.MyFile;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

	@Autowired
	FileDAO fileRepository;
	
	

	public FileController(FileDAO fileRepository) {
		this.fileRepository = fileRepository;
	}

	@PostMapping
	public String create(@RequestBody MyFile file) {
		fileRepository.save(file);
		return "File is created";
	}
	
	@GetMapping("/api/file")
	public Optional<MyFile>getFileById(@RequestParam Long file_id) {
		Optional<MyFile> file = fileRepository.findById(file_id);
		return file;
	}

}
