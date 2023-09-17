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

import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.dto.DirectoryDTO;
import com.projet.edp.fileTree.dto.DirectoryDTOConversion;
import com.projet.edp.fileTree.service.DirectoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RestController
public class DirectoryController {

	@Autowired
	DirectoryService directoryService;

	DirectoryDTOConversion directoryDTOConversion;

	public DirectoryController(DirectoryService directoryService) {
		this.directoryService = directoryService;
		this.directoryDTOConversion = new DirectoryDTOConversion();
	}

	@PostMapping("/directory/save")
	public ResponseEntity<DirectoryDTO> create(@RequestBody DirectoryDTO directoryDTO) {
		Directory directory = directoryDTOConversion.convertDTOtoEntities(directoryDTO);
		directoryService.save(directory);
		return new ResponseEntity<DirectoryDTO>(directoryDTO, HttpStatus.CREATED);
	}

	@GetMapping("/directory")
	public ResponseEntity<DirectoryDTO> getDirectoryById(@RequestParam Long directory_id) {
		Directory directory = directoryService.findDirectoryById(directory_id).get();
		DirectoryDTO directoryDTO = directoryDTOConversion.convertEntityToDTO(directory);
		return ResponseEntity.ok(directoryDTO);
	}
}
