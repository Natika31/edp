package com.projet.edp.fileViewer.ui;

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

import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.dto.FileDTO;
import com.projet.edp.fileViewer.dto.FileDTOConversion;
import com.projet.edp.fileViewer.service.FileContentService;
import com.projet.edp.fileViewer.service.FileService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
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

	@PostMapping("/file/save")
	public ResponseEntity<FileDTO> create(@RequestBody FileDTO fileDTO) {
		MyFile postRequest = fileDTOConversion.convertDTOtoEntities(fileDTO);
		MyFile file = fileService.save(postRequest);
		FileDTO postResponse = fileDTOConversion.convertEntityToDTO(file);
		return new ResponseEntity<FileDTO>(postResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/file")
	public ResponseEntity<FileDTO> getFileById(@RequestParam Long file_id) {
		MyFile file = fileService.findFileById(file_id).get();
		FileDTO fileDTO = fileDTOConversion.convertEntityToDTO(file);
		return ResponseEntity.ok(fileDTO);
	}

}
