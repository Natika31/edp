package com.projet.edp.user.ui;

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
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.user.domain.MyUser;
import com.projet.edp.user.dto.UserDTOConversion;
import com.projet.edp.user.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private static UserDTOConversion userDTOConversion ;

	private static ObjectMapper mapperJSON;

	private Directory rootDirectory;

	private Directory childDirectory;

	private FileContent fileContent;

	private FileTreeItem childFile; 

	private MyUser user;

	@BeforeAll
	public static void setup() {
		userDTOConversion = new UserDTOConversion();
		mapperJSON = new ObjectMapper();
	}

	@AfterAll
	public static void tearDown() {
		userDTOConversion = null;
		mapperJSON = null;    
	}


	@BeforeEach 
	public void init() throws FileNotFoundException, IOException {
		rootDirectory = new Directory("home", "/home");
		rootDirectory.setItem_id(1L);

		childDirectory = new Directory("Henri Salvador", "/home/henri_salvador");
		childDirectory.setItem_id(2L);

		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);

		childFile = new MyFile("Dans mon ile", "/home/henri_salvador/dans_mon_ile.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent );
		childFile.setItem_id(3L);

		user = new MyUser("toto", "toto@me", rootDirectory );
		user.setUser_id(1L);

	}

	@AfterEach
	public void teardown() {
		user = null;
		rootDirectory = null;
		childDirectory = null;
		fileContent = null;
		childFile = null;
	}

	@Test
	public void testGetUserById_EmptyRootDirectory() throws Exception {

		when(userService.findUserById(1L)).thenReturn(Optional.of(user));

		String jsonUserDTO = mapperJSON.writeValueAsString(userDTOConversion.convertEntityToDTO(user));

		this.mockMvc.perform(get("/api/user?user_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonUserDTO)));
	}

	@Test
	public void testGetUserById_RootDirectoryContainsDirectoryContainsFile() throws Exception {

		childDirectory.addChildren(childFile);
		rootDirectory.addChildren(childDirectory);

		when(userService.findUserById(1L)).thenReturn(Optional.of(user));

		String jsonUserDTO = mapperJSON.writeValueAsString(userDTOConversion.convertEntityToDTO(user));

		this.mockMvc.perform(get("/api/user?user_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonUserDTO)));	
	}

	@Test
	public void testGetUserById_RootDirectoryContainsDirectoryAndFile() throws Exception {

		rootDirectory.addChildren(childFile);
		rootDirectory.addChildren(childDirectory);

		when(userService.findUserById(1L)).thenReturn(Optional.of(user));

		String jsonUserDTO = mapperJSON.writeValueAsString(userDTOConversion.convertEntityToDTO(user));

		this.mockMvc.perform(get("/api/user?user_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonUserDTO)));	
	}


}
