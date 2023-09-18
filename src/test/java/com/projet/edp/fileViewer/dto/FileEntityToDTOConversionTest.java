package com.projet.edp.fileViewer.dto;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.accessRight.domain.AccessRight;
import com.projet.edp.accessRight.domain.AccessRightKey;
import com.projet.edp.contact.domain.MyUser;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;

class FileEntityToDTOConversionTest extends FileDTOConversion{

	private static FileDTOConversion fileDTOConversion ;

	private FileContent fileContent;

	private MyFile file;

	private byte[] binaryArray;

	private MyUser user1;
	
	private MyUser user2;

	private AccessRight ar1;


	@BeforeAll
	static void setup() {
		fileDTOConversion = new FileDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		fileDTOConversion = null;
	}

	@BeforeEach
	void setUp() throws Exception {

		//Create a file binary content 
		fileContent = new FileContent();
		binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		fileContent.setFile_content_id(1L);
		//Create a file
		file = new MyFile("Dans mon ile","/home/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf",fileContent);
		file.setItem_id(1L);
		//Create an access right 
		//Create a user sender
		Directory rootAToto = new Directory("home", "/home");
		rootAToto.addChildren(file);
		user1 =  new MyUser("toto", "toto", "myPwd", "toto@me.com", rootAToto);
		user1.setRecipient_id(1L);
		//Create a user recipient
		Directory rootATata = new Directory("home", "/home");
		user2 =  new MyUser("tata", "tata", "myPwdtata", "tata@me.com", rootATata);
		user2.setRecipient_id(2L);
		ar1 = new AccessRight(new AccessRightKey(file.getItem_id(),user2.getRecipient_id()),user1.getRecipient_id());
		file.addAccessRight(ar1);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.binaryArray=null;
		this.fileContent=null;
		this.file=null;
		this.user1=null;
		this.user2=null;
		this.ar1 = null;
	}


	@Test
	public void whenConvertFileEntityToFileDto_thenCorrect() throws FileNotFoundException, IOException {

		//WHEN Convert entity to DTO
		FileDTO actualFileDTO = fileDTOConversion.convertEntityToDTO(file);

		//THEN 
		//Item
		//item_id
		assertNotNull(file.getItem_id());
		assertEquals(file.getItem_id(), Long.valueOf(actualFileDTO.getItem_id()));
		//item_name
		assertNotNull(file.getName());
		assertEquals(file.getName(), actualFileDTO.getName());
		//item_local_path
		assertNotNull(file.getFile_origin_path());
		assertEquals(file.getFile_origin_path(), actualFileDTO.getFile_origin_path());	
		//item_type
		assertNotNull(file.getItem_type());
		assertEquals(file.getItem_type(), actualFileDTO.getItem_type());
		//file access_right
		assertNotNull(file.getAccessRights());
		assertEquals(file.getAccessRights().size(), actualFileDTO.getAccessRights().size());
		assertNotNull(file.getAccessRights().get(0));
		assertEquals(file.getAccessRights().get(0).getSendeurId(), actualFileDTO.getAccessRights().get(0).getSendeurId());
		assertEquals(file.getAccessRights().get(0).getId().getRecipient_id(), actualFileDTO.getAccessRights().get(0).getId().getRecipient_id());
		assertEquals(file.getAccessRights().get(0).getId().getItem_id(), actualFileDTO.getAccessRights().get(0).getId().getItem_id());
		//File
		//file_format
		assertNotNull(file.getFile_format());
		assertEquals(file.getFile_format(), actualFileDTO.getFile_format());
		//binary_content
		assertNotNull(file.getFile_content().getBinary_content());
		assertArrayEquals(file.getFile_content().getBinary_content(), actualFileDTO.getBinary_content());
	}
}