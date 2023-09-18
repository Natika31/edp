package com.projet.edp.accessRight.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.edp.accessRight.domain.AccessRight;
import com.projet.edp.accessRight.service.AccessRightService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RestController
public class AccessRightController {

	@Autowired
	AccessRightService accessRightService;
	
	public AccessRightController(AccessRightService accessRightService) {
		this.accessRightService = accessRightService;
	}
	
	@PostMapping("/accessRight/save")
	public ResponseEntity<AccessRight> create(@RequestBody AccessRight accessRight) {
		accessRightService.save(accessRight);
		return new ResponseEntity<AccessRight>(accessRight, HttpStatus.CREATED);
	}
	
	@GetMapping("/accessRights")
	public ResponseEntity<List<AccessRight>> getAllAccessRights() {
		List<AccessRight> accessRights = accessRightService.findAll();
		return ResponseEntity.ok(accessRights);
	}
}
