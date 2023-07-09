package com.projet.edp.fileViewer.ui;

import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.File;
import java.util.List;
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

	@GetMapping("/files")
	public List<File> getAllFiles() {
		List<File> files = fileRepository.findAll();
		return files;
	}

}
