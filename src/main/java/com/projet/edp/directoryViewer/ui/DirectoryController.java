package com.projet.edp.directoryViewer.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.edp.directoryViewer.domain.Directory;
import com.projet.edp.directoryViewer.service.DirectoryService;


@RestController
public class DirectoryController {
	
	@Autowired
	DirectoryService directoryService;
	
	public DirectoryController(DirectoryService directoryService) {
		this.directoryService = directoryService;
	}

	@PostMapping("/api/directory/save")
	public String create(@RequestBody Directory directory) {
		directoryService.save(directory);
		return "Directory is created";
	}
	
	@GetMapping("/api/directory")
	public ResponseEntity<Directory> getDirectoryById(@RequestParam Long directory_id) {
		return ResponseEntity.ok(directoryService.findDirectoryById(directory_id).get());
	}

}
