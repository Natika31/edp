package com.projet.edp.group.ui;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.group.domain.MyGroup;
import com.projet.edp.group.dto.GroupDTOConversion;
import com.projet.edp.group.service.GroupService;
import com.projet.edp.user.domain.MyUser;
import com.projet.edp.user.dto.UserDTOConversion;

@WebMvcTest(GroupController.class)
class GroupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GroupService groupService;

	private static GroupDTOConversion groupDTOConversion ;

	private static ObjectMapper mapperJSON;

	private Directory rootDirectory;

	private Directory childDirectory1;
	
	private Directory childDirectory2;

	private FileContent fileContent;

	private FileTreeItem childFile; 

	private MyUser user1;

	private MyUser user2;

	private MyUser user3;
	
	private MyGroup group1;

	private MyGroup group2;


	@BeforeAll
	public static void setup() {
		groupDTOConversion = new GroupDTOConversion();
		mapperJSON = new ObjectMapper();
	}

	@AfterAll
	public static void tearDown() {
		groupDTOConversion = null;
		mapperJSON = null;    
	}

	@BeforeEach 
	public void init() throws FileNotFoundException, IOException {
		rootDirectory = new Directory("home", "/home");
		rootDirectory.setItem_id(1L);

		childDirectory1 = new Directory("Henri Salvador", "/home/henri_salvador");
		childDirectory1.setItem_id(2L);

		childDirectory2 = new Directory("The Beatles", "/home/the_beatles");
		childDirectory2.setItem_id(3L);

		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);

		childFile = new MyFile("Dans mon ile", "/home/henri_salvador/dans_mon_ile.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent );
		childFile.setItem_id(3L);

		user1 = new MyUser("toto", "toto@me", rootDirectory );
		user1.setUser_id(1L);

		user2 = new MyUser("tata", "tata@me", rootDirectory );
		user2.setUser_id(2L);

		childDirectory1.addChildren(childFile);
		user2.getRoot().addChildren(childDirectory1);
		user2.getRoot().addChildren(childDirectory2);

		user3 = new MyUser("titi", "titi@me", rootDirectory );
		user3.setUser_id(3L);
		user3.getRoot().addChildren(childDirectory1);
		childDirectory1.addChildren(childFile);
		childDirectory1.addChildren(childDirectory2);

		group1 = new MyGroup("famille");
		group1.setGroup_id(1L);

		group2 = new MyGroup("travail");
		group2.setGroup_id(2L);



	}

	@AfterEach
	public void teardown() {
		fileContent = null;
		childFile = null;
		childDirectory1 = null;
		childDirectory2 = null;
		rootDirectory = null;
		user1 = null;
		user2 = null;
		user3 = null;
		group1 = null;
		group2 = null;
	}

	@Test
	void testGetGroupById_EmptyGroup() throws Exception {
		when(groupService.findGroupById(1L)).thenReturn(Optional.of(group1));

		String jsonGroupDTO = mapperJSON.writeValueAsString(groupDTOConversion.convertEntityToDTO(group1));

		this.mockMvc.perform(get("/api/group?group_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonGroupDTO)));	
	}
	
	@Test
	void testGetGroupById_GroupWithOneUser() throws Exception {
		group1.addMember(user1);
		when(groupService.findGroupById(1L)).thenReturn(Optional.of(group1));

		String jsonGroupDTO = mapperJSON.writeValueAsString(groupDTOConversion.convertEntityToDTO(group1));

		this.mockMvc.perform(get("/api/group?group_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonGroupDTO)));	
	}
	
	@Test
	void testGetGroupById_addMembers() throws Exception {
		group1.addMember(user1);
		group1.addMember(user2);
		group1.addMember(user3);
		when(groupService.findGroupById(1L)).thenReturn(Optional.of(group1));

		String jsonGroupDTO = mapperJSON.writeValueAsString(groupDTOConversion.convertEntityToDTO(group1));

		this.mockMvc.perform(get("/api/group?group_id=1")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(jsonGroupDTO)));	
	}
}
