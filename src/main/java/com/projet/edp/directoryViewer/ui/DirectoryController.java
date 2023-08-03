package com.projet.edp.directoryViewer.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.projet.edp.directoryViewer.domain.Directory;
import com.projet.edp.directoryViewer.dto.DirectoryDTO;
import com.projet.edp.directoryViewer.dto.DirectoryDTOConversion;
import com.projet.edp.directoryViewer.service.DirectoryService;


@RestController
public class DirectoryController {

	@Autowired
	DirectoryService directoryService;

	DirectoryDTOConversion directoryDTOConversion;

	public DirectoryController(DirectoryService directoryService) {
		this.directoryService = directoryService;
		this.directoryDTOConversion = new DirectoryDTOConversion();
	}

	@PostMapping("/api/directory/save")
	public ResponseEntity<DirectoryDTO> create(@RequestBody DirectoryDTO directoryDTO) {
		Directory directory = directoryDTOConversion.convertDTOtoEntities(directoryDTO);
		directoryService.save(directory);
		return new ResponseEntity<DirectoryDTO>(directoryDTO, HttpStatus.CREATED);
	}

	@GetMapping("/api/directory")
	public ResponseEntity<DirectoryDTO> getDirectoryById(@RequestParam Long directory_id) {
		Directory directory = directoryService.findDirectoryById(directory_id).get();
		DirectoryDTO directoryDTO = directoryDTOConversion.convertEntityToDTO(directory);
		return ResponseEntity.ok(directoryDTO);
	}
}
