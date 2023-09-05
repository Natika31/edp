package com.projet.edp.fileTree.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.fileTree.dto.FileDTO;
import com.projet.edp.fileTree.dto.FileDTOConversion;
import com.projet.edp.fileTree.service.FileContentService;
import com.projet.edp.fileTree.service.FileService;

@RestController
public class FileViewerController {

	@Autowired
	FileService fileService;
	
	@Autowired
	FileContentService fileContentService;

	FileDTOConversion fileDTOConversion;
	
	public FileViewerController(FileService fileService) {
		this.fileService = fileService;
		this.fileDTOConversion =  new FileDTOConversion();
	}

	@PostMapping("/api/file/save")
	public ResponseEntity<FileDTO> create(@RequestBody FileDTO fileDTO) {
		MyFile postRequest = fileDTOConversion.convertDTOtoEntities(fileDTO);
		MyFile file = fileService.save(postRequest);
		FileDTO postResponse = fileDTOConversion.convertEntityToDTO(file);
		return new ResponseEntity<FileDTO>(postResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/file")
	public ResponseEntity<FileDTO> getFileById(@RequestParam Long file_id) {
		MyFile file = fileService.findFileById(file_id).get();
		FileDTO fileDTO = fileDTOConversion.convertEntityToDTO(file);
		return ResponseEntity.ok(fileDTO);
	}

}
