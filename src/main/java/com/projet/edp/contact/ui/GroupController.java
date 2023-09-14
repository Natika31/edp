package com.projet.edp.contact.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.edp.contact.domain.MyGroup;
import com.projet.edp.contact.dto.GroupDTO;
import com.projet.edp.contact.dto.GroupDTOConversion;
import com.projet.edp.contact.service.GroupService;

@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	private GroupDTOConversion groupDTOConversion;

	public GroupController(GroupService groupService) {
		this.groupService = groupService;
		this.groupDTOConversion = new GroupDTOConversion();
	}
	
	@PostMapping("/api/group/save")
	public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO groupDTO) {
		MyGroup postRequest = groupDTOConversion.convertDTOtoEntities(groupDTO);
		MyGroup group = groupService.save(postRequest);
		return new ResponseEntity<GroupDTO>(groupDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/group")
	public ResponseEntity<GroupDTO> getGroupById(@RequestParam Long group_id) {
		MyGroup group = groupService.findGroupById(group_id).get();
		GroupDTO groupDTO = groupDTOConversion.convertEntityToDTO(group);
		return ResponseEntity.ok(groupDTO);
	}
	
	@GetMapping("/api/groups")
	public ResponseEntity<List<GroupDTO>> getAllGroups() {
		List<MyGroup> groups = groupService.findAll();
		List<GroupDTO> groupDTOs = groupDTOConversion.convertEntityToDTO(groups);
		return ResponseEntity.ok(groupDTOs);
	}
	
}
