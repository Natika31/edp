package com.projet.edp.fileViewer.ui;

import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.File;
import java.util.List;
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
	public String create(@RequestBody File file) {
		fileRepository.save(file);
		return "File is created";
	}
	
	@GetMapping("/api/file")
	public Optional<File>getFileById(@RequestParam Long file_id) {
		Optional<File> file = fileRepository.findById(file_id);
		return file;
	}

}
