package com.projet.edp.contact.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.contact.domain.MyRecipient;

class RecipientDTOtoEntityConversionTest extends RecipientDTOConversion {

	private static RecipientDTOConversion treeItemDTOConversion ;

	private MyRecipient childDItem;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		treeItemDTOConversion = new RecipientDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		treeItemDTOConversion = null;
	}

	@BeforeEach
	void setUp() throws Exception {


		//Create a recipient item
		childDItem = new MyRecipient("recipient");
		childDItem.setItem_id(1L);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.childDItem=null;
	}

	@Test
	void testConvertEntityToDTO() {
		//WHEN Convert entity to DTO
		RecipientDTO actualRecipientDTO = treeItemDTOConversion.convertEntityToDTO(childDItem);

		//THEN 
		//Item
		//item_id
		assertNotNull(childDItem.getItem_id());
		assertEquals(childDItem.getItem_id(), Long.valueOf(actualRecipientDTO.getItem_id()));
		//item_name
		assertNotNull(childDItem.getName());
		assertEquals(childDItem.getName(), actualRecipientDTO.getName());	
	}

}
