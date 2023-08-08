package com.projet.edp.fileTree.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.service.FileTreeService;

@RestController
public class FileTreeController {
	
	@Autowired
	FileTreeService fileTreeService;

	public FileTreeController(FileTreeService fileTreeService) {
		this.fileTreeService = fileTreeService;
	}
	
	@PostMapping("/api/file-tree/save")
	public ResponseEntity<FileTreeItem> create(@RequestBody FileTreeItem fileTreeItem) {
		fileTreeService.save(fileTreeItem);
		return new ResponseEntity<FileTreeItem>(fileTreeItem, HttpStatus.CREATED);
	}

	@GetMapping("/api/file-tree")
	public ResponseEntity<FileTreeItem> getItemById(@RequestParam Long item_id) {
		FileTreeItem fileTreeItem = fileTreeService.findFileTreeItemById(item_id).get();
		return ResponseEntity.ok(fileTreeItem);
	}
}
