package com.projet.edp.fileViewerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.dto.FileDTO;
import com.projet.edp.fileViewer.dto.FileDTOConversion;
import com.projet.edp.userDirectory.domain.MyUser;
import com.projet.edp.userDirectory.dto.UserDTOConversion;

class FileDTOConversionTest {

	private static FileDTOConversion fileDTOConversion = new FileDTOConversion();
	
	private FileContent fileContent;
	
	private MyFile file;

	private byte[] binaryArray;

	private FileDTO fileDTO;
	
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
		//Entity
		//Create a file binary content 
		fileContent = new FileContent();
		binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		fileContent.setFile_content_id(1L);
		//Create a file
		file = new MyFile("Dans mon ile","/home/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf",fileContent);
		file.setItem_id(1L);
		
		//DTO
		//create a file item DTO
		fileDTO = new FileDTO("1", "file", "/home/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", binaryArray );

		
	}

	@AfterEach
	void tearDown() throws Exception {
		this.binaryArray=null;
		this.fileContent=null;
		this.file=null;
		this.fileDTO=null;
	}


	@Test
	public void whenConvertFileEntityToFileDto_thenCorrect() throws FileNotFoundException, IOException {

		//WHEN Convert entity to DTO
		FileDTO fileDTO = fileDTOConversion.convertEntityToDTO(file);
		
		//THEN 
		//Item
//		item_id
		assertNotNull(file.getItem_id());
		assertEquals(file.getItem_id(), Long.valueOf(fileDTO.getItem_id()));
//		item_name
		assertNotNull(file.getName());
		assertEquals(file.getName(), fileDTO.getName());
//		item_local_path
		assertNotNull(file.getFile_origin_path());
		assertEquals(file.getFile_origin_path(), fileDTO.getFile_origin_path());	
//		item_type
		assertNotNull(file.getItem_type());
		assertEquals(file.getItem_type(), fileDTO.getItem_type());
		//File
//		file_format
		assertNotNull(file.getFile_format());
		assertEquals(file.getFile_format(), fileDTO.getFile_format());
//		binary_content
		assertNotNull(file.getFile_content().getBinary_content());
		assertArrayEquals(file.getFile_content().getBinary_content(), fileDTO.getBinary_content());
		
	}
	
	@Test
	void test_GivenFile_WhenConvertDTOToEntity_ThenReturnFileItemDTO() throws FileNotFoundException, IOException {
	 
		//WHEN Convert DTO to entity
		MyFile file = fileDTOConversion.convertDTOtoEntities(fileDTO);

		//THEN 
		//Item
//		item_id
		assertNotNull(fileDTO.getItem_id());
		assertEquals(fileDTO.getItem_id(), String.valueOf(file.getItem_id()));
//		item_name
		assertNotNull(fileDTO.getName());
		assertEquals(fileDTO.getName(), file.getName());
//		item_local_path
		assertNotNull(fileDTO.getFile_origin_path());
		assertEquals(fileDTO.getFile_origin_path(), file.getFile_origin_path());	
//		item_type
		assertNotNull(fileDTO.getItem_type());
		assertEquals(fileDTO.getItem_type(), file.getItem_type());
		//File
//		file_format
		assertNotNull(fileDTO.getFile_format());
		assertEquals(fileDTO.getFile_format(), file.getFile_format());
//		binary_content
		assertNotNull(fileDTO.getBinary_content());
		assertArrayEquals(fileDTO.getBinary_content(), file.getFile_content().getBinary_content());
	}
}
