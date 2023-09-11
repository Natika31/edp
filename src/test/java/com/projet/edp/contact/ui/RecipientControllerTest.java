package com.projet.edp.contact.ui;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.edp.contact.domain.MyRecipient;
import com.projet.edp.contact.dto.RecipientDTOConversion;
import com.projet.edp.contact.service.RecipientService;

@WebMvcTest(RecipientController.class)
class RecipientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecipientService recipientService;

	private static RecipientDTOConversion recipientDTOConversion ;

	private static ObjectMapper mapperJSON;

	private MyRecipient recipient; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		recipientDTOConversion = new RecipientDTOConversion();
		mapperJSON = new ObjectMapper();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		recipientDTOConversion = null;
		mapperJSON = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		recipient = new MyRecipient("contacts");
		recipient.setItem_id(1L);
	}

	@AfterEach
	void tearDown() throws Exception {
		recipient=null;
	}

	@Test
	void test() throws Exception {
		when(recipientService.findRecipientById(1L)).thenReturn(Optional.of(recipient));

		String jsonRecipientDTO = mapperJSON.writeValueAsString(recipientDTOConversion.convertEntityToDTO(recipient));

		this.mockMvc.perform(get("/api/recipient?item_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonRecipientDTO)));	
	}

}
