package com.projet.edp.user.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.edp.user.domain.MyUser;
import com.projet.edp.user.dto.UserDTO;
import com.projet.edp.user.dto.UserDTOConversion;
import com.projet.edp.user.service.UserService;


@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	private UserDTOConversion userDTOConversion;

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
