package com.projet.edp.userDirectory.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.projet.edp.userDirectory.domain.MyUser;
import com.projet.edp.userDirectory.dto.UserDTO;
import com.projet.edp.userDirectory.dto.UserDTOConversion;
import com.projet.edp.userDirectory.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	UserDTOConversion userDTOConversion;

	public UserController(UserService userService) {
		this.userService = userService;
		this.userDTOConversion = new UserDTOConversion();
	}
	
	@PostMapping("/api/user/save")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
		MyUser postRequest = userDTOConversion.convertDTOtoEntities(userDTO);
		MyUser user = userService.save(postRequest);
		UserDTO postResponse = userDTOConversion.convertEntityToDTO(user);
		return new ResponseEntity<UserDTO>(postResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/user")
	public ResponseEntity<UserDTO> getUserById(@RequestParam Long user_id) {
		MyUser user = userService.findUserById(user_id).get();
		UserDTO userDTO = userDTOConversion.convertEntityToDTO(user);
		return ResponseEntity.ok(userDTO);
	}
	
}
