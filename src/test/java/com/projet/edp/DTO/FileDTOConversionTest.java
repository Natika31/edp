package com.projet.edp.DTO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.projet.edp.fileTree.domain.FileContent;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.fileTree.dto.FileDTO;
import com.projet.edp.fileTree.dto.FileDTOConversion;

class FileDTOConversionTest {

	private FileDTOConversion fileDTOConversion = new FileDTOConversion();
	

	@Test
	public void whenConvertFileEntityToFileDto_thenCorrect() throws FileNotFoundException, IOException {
		FileContent fileContent = new FileContent();;
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		MyFile file = new MyFile();
		file.setItem_id(1L);
		file.setItem_name("file");
		file.setItem_local_path("/home/file.pdf");
		file.setFile_format("pdf");
		file.setFile_origin_path("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		file.setFile_content(fileContent);
		
		FileDTO fileDTO = fileDTOConversion.convertEntityToDTO(file);
		
		assertNotNull(file.getItem_id());
		assertEquals(file.getItem_id(), Long.valueOf(fileDTO.getItem_id()));
		assertNotNull(file.getItem_name());
		assertEquals(file.getItem_name(), fileDTO.getItem_name());
		assertEquals(file.getFile_format(), fileDTO.getFile_format());
		assertArrayEquals(file.getFile_content().getBinary_content(), fileDTO.getBinary_content());
	}
	
	@Test
	void test_GivenFile_WhenConvertDTOToEntity_ThenReturnFileItemDTO() throws FileNotFoundException, IOException {
		//create an item DTO
		FileContent fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		FileDTO fileDTO = new FileDTO("1", "file", "/home/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", binaryArray );
		
		MyFile file = fileDTOConversion.convertDTOtoEntities(fileDTO);

		assertNotNull(fileDTO.getItem_id());
		assertEquals(file.getItem_id(), Long.valueOf(fileDTO.getItem_id()));
		assertNotNull(fileDTO.getItem_name());
		assertEquals(file.getItem_name(), fileDTO.getItem_name());
		assertEquals(file.getFile_format(), fileDTO.getFile_format());
		assertArrayEquals(file.getFile_content().getBinary_content(), fileDTO.getBinary_content());
	}
}
