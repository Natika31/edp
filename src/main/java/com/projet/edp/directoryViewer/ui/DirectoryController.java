package com.projet.edp.directoryViewer.ui;

import java.util.List;
import org.modelmapper.ModelMapper;
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
import com.projet.edp.directoryViewer.service.DirectoryService;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.dto.FileDTO;


@RestController
public class DirectoryController {

	@Autowired
	DirectoryService directoryService;

	@Autowired
	private ModelMapper modelMapper;

	public DirectoryController(DirectoryService directoryService) {
		this.directoryService = directoryService;
	}

	@PostMapping("/api/directory/save")
	public ResponseEntity<DirectoryDTO> create(@RequestBody DirectoryDTO directoryDTO) {
		Directory directory = convertDTOtoEntities(directoryDTO);
		directoryService.save(directory);
		return new ResponseEntity<DirectoryDTO>(directoryDTO, HttpStatus.CREATED);
	}

	@GetMapping("/api/directory")
	public ResponseEntity<DirectoryDTO> getDirectoryById(@RequestParam Long directory_id) {
		Directory directory = directoryService.findDirectoryById(directory_id).get();
		DirectoryDTO directoryDTO = convertEntityToDTO(directory);
		return ResponseEntity.ok(directoryDTO);
	}

	private DirectoryDTO convertEntityToDTO(Directory directory) {
		List<MyFile> children = directory.getChildren();
		DirectoryDTO directoryDTO = this.modelMapper.map(directory, DirectoryDTO.class);
		for (MyFile myFile : children) {
			FileDTO myFileDTO = this.modelMapper.map(myFile, FileDTO.class);
			directoryDTO.addChildrenDTO(myFileDTO);
		}
		return directoryDTO;
	}
	
	private Directory convertDTOtoEntities(DirectoryDTO directoryDTO) {
		List<FileDTO> childrenDTO = directoryDTO.getChildren();
		Directory directory = modelMapper.map(directoryDTO, Directory.class);
		for (FileDTO fileDTO : childrenDTO) {
			MyFile file = this.modelMapper.map(fileDTO, MyFile.class);
			directory.addChildren(file);
		}
		return directory;
	}

}
