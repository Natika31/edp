package com.projet.edp.contact.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.edp.contact.domain.MyRecipient;
import com.projet.edp.contact.dto.RecipientDTO;
import com.projet.edp.contact.dto.RecipientDTOConversion;
import com.projet.edp.contact.service.RecipientService;

@RestController
public class RecipientController {
	
	@Autowired
	RecipientService recipientService;
	
	private RecipientDTOConversion recipientDTOConversion;

	public RecipientController(RecipientService recipientService) {
		this.recipientService = recipientService;
		this.recipientDTOConversion = new RecipientDTOConversion();
	}
	
	@PostMapping("/api/recipient/save")
	public ResponseEntity<RecipientDTO> create(@RequestBody RecipientDTO recipientDTO) {
		MyRecipient recipient = recipientDTOConversion.convertDTOtoEntities(recipientDTO);
		recipientService.save(recipient);
		return new ResponseEntity<RecipientDTO>(recipientDTO, HttpStatus.CREATED);
	}

	@GetMapping("/api/recipient")
	public ResponseEntity<RecipientDTO> getItemById(@RequestParam String item_id) {
		MyRecipient recipient = recipientService.findRecipientById(Long.valueOf(item_id)).get();
		RecipientDTO recipientDTO = recipientDTOConversion.convertEntityToDTO(recipient);
		return ResponseEntity.ok(recipientDTO);
	}
	

}
