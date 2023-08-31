package com.projet.edp.directoryViewerTest;

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
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileContent;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.fileTree.dto.TreeDTOConversion;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.service.FileTreeService;
import com.projet.edp.fileTree.ui.FileTreeController;

@WebMvcTest(FileTreeController.class)
class FileTreeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileTreeService fileTreeService;

	private Directory rootDirectory;

	private Directory childDirectory1;

	private FileContent fileContent;

	private FileTreeItem childFile; 

	private static TreeDTOConversion treeDTOConversion ;

	private static ObjectMapper mapperJSON;

	@BeforeAll
	public static void setup() {
		treeDTOConversion = new TreeDTOConversion();
		mapperJSON = new ObjectMapper();
	}

	@AfterAll
	public static void tearDown() {
		treeDTOConversion = null;
		mapperJSON = null;    
	}

	@BeforeEach 
	public void init() throws FileNotFoundException, IOException {
		rootDirectory = new Directory("home", "/home");
		rootDirectory.setItem_id(1L);

		childDirectory1 = new Directory("Henri Salvador", "/home/henri_salvador");
		childDirectory1.setItem_id(2L);

		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);

		childFile = new MyFile("Dans mon ile", "/home/henri_salvador/dans_mon_ile.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent );
		childFile.setItem_id(1L);

	}

	@AfterEach
	public void teardown() {
		rootDirectory = null;
		childDirectory1 = null;
		fileContent = null;
		childFile = null;
	}

	@Test
	public void testGivenEmptyDirectory_whenHTTPRequestGETTreeItem_thenGetStoredDirectory() throws Exception {

		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));

		String jsonItemDTO = mapperJSON.writeValueAsString(treeDTOConversion.convertEntityToDTO(rootDirectory));

		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonItemDTO)));
	}

	@Test
	public void testGivenDirectoryContainsDirectory_whenRequestGETTreeItem_thenGetDirectoryContainsDirectory() throws Exception {

		rootDirectory.addChildren(childDirectory1);

		String jsonItemDTO = mapperJSON.writeValueAsString(treeDTOConversion.convertEntityToDTO(rootDirectory));

		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));
		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonItemDTO)))
		;
	}

	@Test
	public void testGivenDirectoryContainsDirectoryContainsFile_whenRequestGETTreeItem_thenGetDirectoryContainsDirectoryContainsFile() throws Exception {

		childDirectory1.addChildren(childFile);
		rootDirectory.addChildren(childDirectory1);

		String jsonItemDTO = mapperJSON.writeValueAsString(treeDTOConversion.convertEntityToDTO(rootDirectory));

		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));
		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonItemDTO)));
	}

	@Test
	public void testGivenDirectoryContainsDirectoryAndDirectoryContainsFile_whenRequestGETTreeItem_thenGetDirectoryContainsDirectoryContainsFile() throws Exception {

		childDirectory1.addChildren(childFile);
		rootDirectory.addChildren(childDirectory1);

		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));

		String jsonItemDTO = mapperJSON.writeValueAsString(treeDTOConversion.convertEntityToDTO(rootDirectory));

		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonItemDTO)));
	}

}
