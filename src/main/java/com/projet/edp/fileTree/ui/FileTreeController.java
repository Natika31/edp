package com.projet.edp.fileTree.ui;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.dto.TreeItemDTO;
import com.projet.edp.fileTree.dto.TreeDTOConversion;
import com.projet.edp.fileTree.service.FileTreeService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RestController
public class FileTreeController {
	
	@Autowired
	FileTreeService fileTreeService;
	
	private TreeDTOConversion itemDTOConversion;

	public FileTreeController(FileTreeService fileTreeService) {
		this.fileTreeService = fileTreeService;
		this.itemDTOConversion = new TreeDTOConversion();
	}
	
	@PostMapping("/file-tree/save")
	public ResponseEntity<TreeItemDTO> create(@RequestBody TreeItemDTO fileTreeItemDTO) {
		FileTreeItem fileTreeItem = itemDTOConversion.convertDTOtoEntities(fileTreeItemDTO);
		fileTreeService.save(fileTreeItem);
		return new ResponseEntity<TreeItemDTO>(fileTreeItemDTO, HttpStatus.CREATED);
	}

	@GetMapping("/file-tree")
	public ResponseEntity<TreeItemDTO> getItemById(@RequestParam String item_id) {
		FileTreeItem fileTreeItem = fileTreeService.findFileTreeItemById(Long.valueOf(item_id)).get();
		TreeItemDTO fileTreeItemDTO = itemDTOConversion.convertEntityToDTO(fileTreeItem);
		return ResponseEntity.ok(fileTreeItemDTO);
	}
	
	@GetMapping("/file-tree/name")
	public ResponseEntity<TreeItemDTO> getItemByName(@RequestParam String name) {
		FileTreeItem fileTreeItem = fileTreeService.findFileTreeItemByName(name).get();
		TreeItemDTO fileTreeItemDTO = itemDTOConversion.convertEntityToDTO(fileTreeItem);
		return ResponseEntity.ok(fileTreeItemDTO);
	}
}
