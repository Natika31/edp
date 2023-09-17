package com.projet.edp.fileTree.ui;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.dto.DirectoryDTOConversion;
import com.projet.edp.fileTree.service.DirectoryService;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;

@WithMockUser(value = "toto")
@WebMvcTest(DirectoryController.class)
class DirectoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DirectoryService directoryService;
	
	private static DirectoryDTOConversion directoryDTOConversion;

	private static ObjectMapper mapperJSON;
	
	private Directory rootDirectory;
	
	private FileContent fileContent;

	private FileTreeItem childFile;
	
	@BeforeAll
	public static void setup() {
		directoryDTOConversion = new DirectoryDTOConversion();
		mapperJSON = new ObjectMapper();
	}

	@AfterAll
	public static void tearDown() {
		directoryDTOConversion = null;
		mapperJSON = null;    
	}
	
	@BeforeEach 
	public void init() throws FileNotFoundException, IOException {
		rootDirectory = new Directory("home", "/home");
		rootDirectory.setItem_id(1L);

		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);

		childFile = new MyFile("Dans mon ile", "/home/henri_salvador/dans_mon_ile.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent );
		childFile.setItem_id(2L);
	}

	@AfterEach
	public void teardown() {
		rootDirectory = null;
		fileContent = null;
		childFile = null;
	}


	@Test
	public void givenSelectedDirectory_whenRequestGETEmptyDirectoryIdEquals1_thenGetStoredDirectoryIdEquals1() throws Exception {

		when(directoryService.findDirectoryById(1L)).thenReturn(Optional.of(rootDirectory));
		
		String jsonDirectoryDTO = mapperJSON.writeValueAsString(directoryDTOConversion.convertEntityToDTO(rootDirectory));

		this.mockMvc.perform(get("/api/v1/directory?directory_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonDirectoryDTO)))
		;
	}

	@Test
	public void givenSelectedDirectory_whenRequestGETNonEmptyDirectoryIdEquals2_thenGetStoredDirectoryIdEquals2AndGetItsContent() throws Exception {

		rootDirectory.addChildren(childFile);
		
		String jsonDirectoryDTO = mapperJSON.writeValueAsString(directoryDTOConversion.convertEntityToDTO(rootDirectory));
		
		when(directoryService.findDirectoryById(1L)).thenReturn(Optional.of(rootDirectory));
		this.mockMvc.perform(get("/api/v1/directory?directory_id=1"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonDirectoryDTO)))
		;
	
	}
}
