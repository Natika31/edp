package com.projet.edp.contact.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.contact.domain.MyGroup;
import com.projet.edp.contact.domain.MyRecipient;
import com.projet.edp.contact.domain.MyUser;
import com.projet.edp.fileTree.domain.Directory;

class RecipientEntityToDTOConversionTest extends RecipientDTOConversion {

	private static RecipientDTOConversion recipientDTOConversion ;

	private MyRecipient group;

	private MyRecipient user1;

	private MyRecipient user2;

	private MyRecipient group1;

	private MyRecipient group2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		recipientDTOConversion = new RecipientDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		recipientDTOConversion = null;
	}

	@BeforeEach
	void setUp() throws Exception {		
		Directory rootDirectory = new Directory("home", "/home");	
		user1 = new MyUser("toto", "toto@me", "loginAToto", "secret", rootDirectory); 
		user1.setRecipient_id(1L);

		user2 = new MyUser("tata", "tata@me", "loginATata", "secret", rootDirectory); 
		user2.setRecipient_id(2L);

		group = new MyGroup("allrecipients");
		group.setRecipient_id(3L);
		
		group1 = new MyGroup("family");
		group1.setRecipient_id(4L);
		
		group2 = new MyGroup("work");
		group2.setRecipient_id(5L);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.user1=null;
		this.user2=null;
		this.group=null;
	}
	
	
	@Test
	void testUser() {
		//WHEN Convert entity to DTO
		RecipientDTO actualRecipientDTO = recipientDTOConversion.convertEntityToDTO(user1);
		
		//THEN 
		//Item
		//item_id
		assertNotNull(actualRecipientDTO.getRecipient_id());
		assertEquals(user1.getRecipient_id(), Long.valueOf(actualRecipientDTO.getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getName());
		assertEquals(user1.getName(), actualRecipientDTO.getName());
		//item_type
		assertNotNull(actualRecipientDTO.getItem_type());
		assertEquals(user1.getItem_type(), actualRecipientDTO.getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers());
		assertEquals(0, actualRecipientDTO.getMembers().size());
	
	}

	@Test
	void testEmptyRecipient() {
		
		//WHEN Convert entity to DTO
		RecipientDTO actualRecipientDTO = recipientDTOConversion.convertEntityToDTO(group);

		//THEN 
		//Item
		//item_id
		assertNotNull(actualRecipientDTO.getRecipient_id());
		assertEquals(group.getRecipient_id(), Long.valueOf(actualRecipientDTO.getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getName());
		assertEquals(group.getName(), actualRecipientDTO.getName());
		//item_type
		assertNotNull(actualRecipientDTO.getItem_type());
		assertEquals(group.getItem_type(), actualRecipientDTO.getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers());
		assertEquals(((MyGroup) group).getMembers(), actualRecipientDTO.getMembers());
	}
	
	@Test
	void testRecipientsAreUsers() {
		((MyGroup) group).addMember(user1);
		((MyGroup) group).addMember(user2);

		//WHEN Convert entity to DTO
		RecipientDTO actualRecipientDTO = recipientDTOConversion.convertEntityToDTO(group);

		//THEN 
		//Item
		//item_id
		assertNotNull(actualRecipientDTO.getRecipient_id());
		assertEquals(group.getRecipient_id(), Long.valueOf(actualRecipientDTO.getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getName());
		assertEquals(group.getName(), actualRecipientDTO.getName());
		//item_type
		assertNotNull(actualRecipientDTO.getItem_type());
		assertEquals(group.getItem_type(), actualRecipientDTO.getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().size());
		assertEquals(((MyGroup) group).getMembers().size(), actualRecipientDTO.getMembers().size());
		
		//user1
		//item_id
		assertNotNull(actualRecipientDTO.getMembers().get(0).getRecipient_id());
		assertEquals(((MyGroup) group).getMembers().get(0).getRecipient_id(), Long.valueOf(actualRecipientDTO.getMembers().get(0).getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getMembers().get(0).getName());
		assertEquals(((MyGroup) group).getMembers().get(0).getName(), actualRecipientDTO.getMembers().get(0).getName());
		//item_type
		assertNotNull(actualRecipientDTO.getMembers().get(0).getItem_type());
		assertEquals(((MyGroup) group).getMembers().get(0).getItem_type(), actualRecipientDTO.getMembers().get(0).getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().get(0).getMembers());
		assertEquals(0, actualRecipientDTO.getMembers().get(0).getMembers().size());
		
		//user2
		//item_id
		assertNotNull(actualRecipientDTO.getMembers().get(1).getRecipient_id());
		assertEquals(((MyGroup) group).getMembers().get(1).getRecipient_id(), Long.valueOf(actualRecipientDTO.getMembers().get(1).getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getMembers().get(1).getName());
		assertEquals(((MyGroup) group).getMembers().get(1).getName(), actualRecipientDTO.getMembers().get(1).getName());
		//item_type
		assertNotNull(actualRecipientDTO.getMembers().get(1).getItem_type());
		assertEquals(((MyGroup) group).getMembers().get(1).getItem_type(), actualRecipientDTO.getMembers().get(1).getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().get(1).getMembers());
		assertEquals(0, actualRecipientDTO.getMembers().get(1).getMembers().size());
	}
	
	@Test
	void testRecipientsAreGroups() {
		((MyGroup) group1).addMember(user1);
		((MyGroup) group2).addMember(user2);
		((MyGroup) group).addMember(group1);
		((MyGroup) group).addMember(group2);

		//WHEN Convert entity to DTO
		RecipientDTO actualRecipientDTO = recipientDTOConversion.convertEntityToDTO(group);

		//THEN 
		//Item
		//item_id
		assertNotNull(actualRecipientDTO.getRecipient_id());
		assertEquals(group.getRecipient_id(), Long.valueOf(actualRecipientDTO.getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getName());
		assertEquals(group.getName(), actualRecipientDTO.getName());
		//item_type
		assertNotNull(actualRecipientDTO.getItem_type());
		assertEquals(group.getItem_type(), actualRecipientDTO.getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().size());
		assertEquals(((MyGroup) group).getMembers().size(), actualRecipientDTO.getMembers().size());
		
		//group1
		//item_id
		assertNotNull(actualRecipientDTO.getMembers().get(0).getRecipient_id());
		assertEquals(((MyGroup) group).getMembers().get(0).getRecipient_id(), Long.valueOf(actualRecipientDTO.getMembers().get(0).getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getMembers().get(0).getName());
		assertEquals(((MyGroup) group).getMembers().get(0).getName(), actualRecipientDTO.getMembers().get(0).getName());
		//item_type
		assertNotNull(actualRecipientDTO.getMembers().get(0).getItem_type());
		assertEquals(((MyGroup) group).getMembers().get(0).getItem_type(), actualRecipientDTO.getMembers().get(0).getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().get(0).getMembers().size());
		assertEquals(((MyGroup) ((MyGroup) group).getMembers().get(0)).getMembers().size(),  actualRecipientDTO.getMembers().get(0).getMembers().size());
		
		//group2
		//item_id
		assertNotNull(actualRecipientDTO.getMembers().get(1).getRecipient_id());
		assertEquals(((MyGroup) group).getMembers().get(1).getRecipient_id(), Long.valueOf(actualRecipientDTO.getMembers().get(1).getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getMembers().get(1).getName());
		assertEquals(((MyGroup) group).getMembers().get(1).getName(), actualRecipientDTO.getMembers().get(1).getName());
		//item_type
		assertNotNull(actualRecipientDTO.getMembers().get(1).getItem_type());
		assertEquals(((MyGroup) group).getMembers().get(1).getItem_type(), actualRecipientDTO.getMembers().get(1).getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().get(1).getMembers());
		assertEquals(((MyGroup) ((MyGroup) group).getMembers().get(1)).getMembers().size(), actualRecipientDTO.getMembers().get(1).getMembers().size());
	}
	
	@Test
	void testRecipientsAreGroupsAndUsers() {
		((MyGroup) group1).addMember(user1);
		((MyGroup) group).addMember(group1);
		((MyGroup) group).addMember(user2);

		//WHEN Convert entity to DTO
		RecipientDTO actualRecipientDTO = recipientDTOConversion.convertEntityToDTO(group);

		//THEN 
		//Item
		//item_id
		assertNotNull(actualRecipientDTO.getRecipient_id());
		assertEquals(group.getRecipient_id(), Long.valueOf(actualRecipientDTO.getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getName());
		assertEquals(group.getName(), actualRecipientDTO.getName());
		//item_type
		assertNotNull(actualRecipientDTO.getItem_type());
		assertEquals(group.getItem_type(), actualRecipientDTO.getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().size());
		assertEquals(((MyGroup) group).getMembers().size(), actualRecipientDTO.getMembers().size());
		
		//group1
		//item_id
		assertNotNull(actualRecipientDTO.getMembers().get(0).getRecipient_id());
		assertEquals(((MyGroup) group).getMembers().get(0).getRecipient_id(), Long.valueOf(actualRecipientDTO.getMembers().get(0).getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getMembers().get(0).getName());
		assertEquals(((MyGroup) group).getMembers().get(0).getName(), actualRecipientDTO.getMembers().get(0).getName());
		//item_type
		assertNotNull(actualRecipientDTO.getMembers().get(0).getItem_type());
		assertEquals(((MyGroup) group).getMembers().get(0).getItem_type(), actualRecipientDTO.getMembers().get(0).getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().get(0).getMembers().size());
		assertEquals(((MyGroup) ((MyGroup) group).getMembers().get(0)).getMembers().size(),  actualRecipientDTO.getMembers().get(0).getMembers().size());
		
		//user2
		//item_id
		assertNotNull(actualRecipientDTO.getMembers().get(1).getRecipient_id());
		assertEquals(((MyGroup) group).getMembers().get(1).getRecipient_id(), Long.valueOf(actualRecipientDTO.getMembers().get(1).getRecipient_id()));
		//item_name
		assertNotNull(actualRecipientDTO.getMembers().get(1).getName());
		assertEquals(((MyGroup) group).getMembers().get(1).getName(), actualRecipientDTO.getMembers().get(1).getName());
		//item_type
		assertNotNull(actualRecipientDTO.getMembers().get(1).getItem_type());
		assertEquals(((MyGroup) group).getMembers().get(1).getItem_type(), actualRecipientDTO.getMembers().get(1).getItem_type());
		//members
		assertNotNull(actualRecipientDTO.getMembers().get(1).getMembers());
		assertEquals(0, actualRecipientDTO.getMembers().get(1).getMembers().size());
	}
	
	
	

}
