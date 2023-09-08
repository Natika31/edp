package com.projet.edp.group.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.edp.group.domain.MyGroup;
import com.projet.edp.group.dto.GroupDTO;
import com.projet.edp.group.dto.GroupDTOConversion;
import com.projet.edp.group.service.GroupService;

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
		GroupDTO postResponse = groupDTOConversion.convertEntityToDTO(group);
		return new ResponseEntity<GroupDTO>(postResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/group")
	public ResponseEntity<GroupDTO> getGroupById(@RequestParam Long group_id) {
		MyGroup group = groupService.findGroupById(group_id).get();
		GroupDTO groupDTO = groupDTOConversion.convertEntityToDTO(group);
		return ResponseEntity.ok(groupDTO);
	}
	
}
