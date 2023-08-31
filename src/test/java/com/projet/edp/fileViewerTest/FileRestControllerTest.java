package com.projet.edp.fileViewerTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.projet.edp.fileTree.domain.FileContent;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.fileTree.dto.FileDTOConversion;
import com.projet.edp.fileTree.service.FileContentService;
import com.projet.edp.fileTree.service.FileService;
import com.projet.edp.fileTree.ui.FileViewerController;

@WebMvcTest(FileViewerController.class)
class FileRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileContentService fileContentService;

	@MockBean
	private FileService fileService;
	
	private static FileDTOConversion fileDTOConversion ;

	private static ObjectMapper mapperJSON;
	
	private FileContent fileContent;

	private MyFile file; 
	
	@BeforeAll
	public static void setup() {
		fileDTOConversion = new FileDTOConversion();
		mapperJSON = new ObjectMapper();
	}

	@AfterAll
	public static void tearDown() {
		fileDTOConversion = null;
		mapperJSON = null;    
	}
	
	@BeforeEach 
	public void init() throws FileNotFoundException, IOException {
		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);

		file = new MyFile("Dans mon ile", "/home/henri_salvador/dans_mon_ile.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent );
		file.setItem_id(1L);
	}

	@AfterEach
	public void teardown() {
		fileContent = null;
		file = null;
	}

	@Test
	void testGivenSelectedFile_whenRequestGETFileIdEquals1_thenGetStoredFileIdEquals1() throws Exception {

		when(fileService.findFileById(1L)).thenReturn(Optional.of(file));
		
		String jsonFileDTO = mapperJSON.writeValueAsString(fileDTOConversion.convertEntityToDTO(file));

		this.mockMvc.perform(get("/api/file?file_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonFileDTO)));
	}
}
