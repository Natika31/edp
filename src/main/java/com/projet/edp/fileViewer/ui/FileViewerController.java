package com.projet.edp.fileViewer.ui;

import com.projet.edp.fileViewer.service.FileService;
import com.projet.edp.fileViewer.domain.MyFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileViewerController {

	@Autowired
	FileService fileService;
	
	public FileViewerController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/api/file/save")
	public String create(@RequestBody MyFile file) {
		fileService.save(file);
		return "File is created";
	}
	
	@GetMapping("/api/file")
	public ResponseEntity<MyFile> getFileById(@RequestParam Long file_id) {
		return ResponseEntity.ok(fileService.findFileById(file_id).get());
	}

}
