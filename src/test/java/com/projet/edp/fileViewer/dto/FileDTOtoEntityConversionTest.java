package com.projet.edp.fileViewer.dto;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.projet.edp.fileViewer.domain.FileContent;

class FileDTOtoEntityConversionTest extends FileDTOConversion {
	
	private static FileDTOConversion fileDTOConversion ;
	
	private FileDTO fileDTO;

	private FileContent fileContent;

	private byte[] binaryArray;
	
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

		//Create a file
		fileDTO = new FileDTO("1", "Dans mon ile","/home/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", binaryArray);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.binaryArray=null;
		this.fileContent=null;
		this.fileDTO=null;
	}


//	@Test
//	void test_GivenFile_WhenConvertDTOToEntity_ThenReturnFileItemDTO() throws FileNotFoundException, IOException {
//
//		//WHEN Convert DTO to entity
//		MyFile actualFile = fileDTOConversion.convertDTOtoEntities(fileDTO);
//
//		//THEN 
//		//Item
//		//item_id
//		assertNotNull(fileDTO.getItem_id());
//		assertEquals(fileDTO.getItem_id(), String.valueOf(actualFile.getItem_id()));
//		//item_name
//		assertNotNull(fileDTO.getName());
//		assertEquals(fileDTO.getName(), actualFile.getName());
//		//item_local_path
//		assertNotNull(fileDTO.getFile_origin_path());
//		assertEquals(fileDTO.getFile_origin_path(), actualFile.getFile_origin_path());	
//		//item_type
//		assertNotNull(fileDTO.getItem_type());
//		assertEquals(fileDTO.getItem_type(), actualFile.getItem_type());
//		//File
//		//file_format
//		assertNotNull(fileDTO.getFile_format());
//		assertEquals(fileDTO.getFile_format(), actualFile.getFile_format());
//		//binary_content
//		assertNotNull(fileDTO.getBinary_content());
//		assertArrayEquals(fileDTO.getBinary_content(), actualFile.getFile_content().getBinary_content());
//	}

}
