package com.projet.edp.fileViewer.ui;

import com.projet.edp.fileViewer.service.FileServiceImpl;
import com.projet.edp.fileViewer.domain.MyFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

	@Autowired
	FileServiceImpl fileServiceImpl;
	
	public FileController(FileServiceImpl fileServiceImpl) {
		this.fileServiceImpl = fileServiceImpl;
	}

	@PostMapping
	public String create(@RequestBody MyFile file) {
		fileServiceImpl.save(file);
		return "File is created";
	}
	
	@GetMapping("/api/file")
	public ResponseEntity<MyFile> getFileById(@RequestParam Long file_id) {
		return ResponseEntity.ok(fileServiceImpl.findFileById(file_id).get());
	}

}
