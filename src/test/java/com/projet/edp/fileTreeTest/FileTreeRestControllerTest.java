package com.projet.edp.fileTreeTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileContent;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.service.FileTreeService;
import com.projet.edp.fileTree.ui.FileTreeController;

@WebMvcTest(FileTreeController.class)
class FileTreeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileTreeService fileTreeService;

	@Test
	void GivenEmptyDirectoryId1_whenHTTPRequestGETTreeItemId1_thenGetStoredDirectoryIdEquals1() throws Exception {
		FileTreeItem rootDirectory = new Directory("home", "/home/");
		rootDirectory.setItem_id(1L);
		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));

		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("item_id\":\"1\",\"item_local_path\":\"/home/\",\"item_name\":\"home\"")));
	}

	@Test
	void GivenDirectoryId1ContainsDirectoryId2_whenRequestGETTreeItemId1_thenGetDirectoryId1ContainsDirectoryId2() throws Exception {
		Directory rootDirectory = new Directory("home", "/home/");
		rootDirectory.setItem_id(1L);
		Directory childDirectory = new Directory("Henri Salvador", "/home/henri_salvador");
		childDirectory.setItem_id(2L);
		rootDirectory.addChildren(childDirectory);
		
		
		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));
		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("item_id\":\"1\",\"item_local_path\":\"/home/\",\"item_name\":\"home\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":["
				+ "{\"item_id\":\"2\",\"item_local_path\":\"/home/henri_salvador\",\"item_name\":\"Henri Salvador\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":[]}"
				+ "]")))
		;
	}
	
	@Test
	void GivenDirectoryId1ContainsDirectoryId2ContainsFileId3_whenRequestGETTreeItemId1_thenGetDirectoryId1ContainsDirectoryId2ContainsFileId3() throws Exception {
		Directory rootDirectory = new Directory("home", "/home/");
		rootDirectory.setItem_id(1L);
		Directory childDirectory = new Directory("Henri Salvador", "/home/henri_salvador");
		childDirectory.setItem_id(2L);
		FileContent fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		FileTreeItem childFile = new MyFile("Dans mon ile", "/home/henri_salvador/dans_mon_ile.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent );
		childFile.setItem_id(3L);
		childDirectory.addChildren(childFile);
		rootDirectory.addChildren(childDirectory);
		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));
		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(
				  "\"item_id\":\"1\",\"item_local_path\":\"/home/\",\"item_name\":\"home\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":["
				+ "{\"item_id\":\"2\",\"item_local_path\":\"/home/henri_salvador\",\"item_name\":\"Henri Salvador\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":["
				+ "{\"item_id\":\"3\",\"item_local_path\":\"/home/henri_salvador/dans_mon_ile.pdf\",\"item_name\":\"Dans mon ile\",\"item_type\":\"class com.projet.edp.fileTree.domain.MyFile\",\"children\":[]}"
				+ "]}"
				+ "]")));
	}
	
	@Test
	void GivenDirectoryId1ContainsDirectoryId2AndDirectoryId3ContainsFileId4_whenRequestGETTreeItemId1_thenGetDirectoryId1ContainsDirectoryId2ContainsFileId3() throws Exception {
		Directory rootDirectory = new Directory("home", "/home/");
		rootDirectory.setItem_id(1L);
		Directory childDirectory1 = new Directory("Henri Salvador", "/home/henri_salvador");
		childDirectory1.setItem_id(2L);
		Directory childDirectory2 = new Directory("Natacha", "/home/henri_salvador/natacha/");
		childDirectory2.setItem_id(3L);
		FileContent fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		FileTreeItem childFile = new MyFile("Dans mon ile", "/home/henri_salvador/dans_mon_ile.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent );
		childFile.setItem_id(3L);
		childDirectory1.addChildren(childFile);
		rootDirectory.addChildren(childDirectory1);
		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(rootDirectory));
		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(
				  "\"item_id\":\"1\",\"item_local_path\":\"/home/\",\"item_name\":\"home\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":["
				+ "{\"item_id\":\"2\",\"item_local_path\":\"/home/henri_salvador\",\"item_name\":\"Henri Salvador\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":["
				+ "{\"item_id\":\"3\",\"item_local_path\":\"/home/henri_salvador/dans_mon_ile.pdf\",\"item_name\":\"Dans mon ile\",\"item_type\":\"class com.projet.edp.fileTree.domain.MyFile\",\"children\":[]}"
				+ "]}"
				+ "]")));
	}

}
