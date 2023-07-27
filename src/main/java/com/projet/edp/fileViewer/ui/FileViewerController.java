package com.projet.edp.fileViewer.ui;

import com.projet.edp.fileViewer.service.FileViewerService;
import com.projet.edp.fileViewer.domain.MyFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileViewerController {

	@Autowired
	FileViewerService fileViewerService;
	
	public FileViewerController(FileViewerService fileViewerService) {
		this.fileViewerService = fileViewerService;
	}

	@PostMapping("/api/file/save")
	public String create(@RequestBody MyFile file) {
		fileViewerService.save(file);
		return "File is created";
	}
	
	@GetMapping("/api/file")
	public ResponseEntity<MyFile> getFileById(@RequestParam Long file_id) {
		return ResponseEntity.ok(fileViewerService.findFileById(file_id).get());
	}

}
