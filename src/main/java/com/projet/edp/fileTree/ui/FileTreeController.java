package com.projet.edp.fileTree.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.projet.edp.fileTree.domain.TreeItem;
import com.projet.edp.fileTree.dto.TreeItemDTO;
import com.projet.edp.fileTree.dto.TreeDTOConversion;
import com.projet.edp.fileTree.service.FileTreeService;

@RestController
public class FileTreeController {
	
	@Autowired
	FileTreeService fileTreeService;
	
	private TreeDTOConversion itemDTOConversion;

	public FileTreeController(FileTreeService fileTreeService) {
		this.fileTreeService = fileTreeService;
		this.itemDTOConversion = new TreeDTOConversion();
	}
	
	@PostMapping("/api/file-tree/save")
	public ResponseEntity<TreeItemDTO> create(@RequestBody TreeItemDTO fileTreeItemDTO) {
		TreeItem treeItem = itemDTOConversion.convertDTOtoEntities(fileTreeItemDTO);
		fileTreeService.save(treeItem);
		return new ResponseEntity<TreeItemDTO>(fileTreeItemDTO, HttpStatus.CREATED);
	}

	@GetMapping("/api/file-tree")
	public ResponseEntity<TreeItemDTO> getItemById(@RequestParam String item_id) {
		TreeItem treeItem = fileTreeService.findFileTreeItemById(Long.valueOf(item_id)).get();
		TreeItemDTO fileTreeItemDTO = itemDTOConversion.convertEntityToDTO(treeItem);
		return ResponseEntity.ok(fileTreeItemDTO);
	}
}
