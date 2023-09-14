package com.projet.edp.contact.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.contact.domain.MyGroup;
import com.projet.edp.contact.domain.MyUser;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;

class GroupEntityToDTOConversionTest {

	private static GroupDTOConversion groupDTOConversion;

	private MyGroup myGroup;
	private MyUser member1;
	private MyUser member2;
	private Directory rootDir;

	private FileContent fileContent;

	private FileTreeItem childFile;

	private FileTreeItem childDirectory;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		groupDTOConversion = new GroupDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		groupDTOConversion = null;
	}


	@BeforeEach
	void setUp() throws Exception {
		//Given an empty group
		myGroup = new MyGroup("famille");
		myGroup.setRecipient_id(1L);
		//Given a root directory
		rootDir = new Directory("home","/home");
		rootDir.setItem_id(1L);
		//Given member 1 and its empty root directory
		member1 = new MyUser("Natacha", "natacha@mail.me", "ncamugli", "seecret", rootDir);
		member1.setRecipient_id(2L);
		//Given member 2 and its empty root directory
		member2 = new MyUser("Robert", "robert@mail.me", "rcamugli", "secret", rootDir);
		member2.setRecipient_id(3L);
		//Create a file binary content 
		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		fileContent.setFile_content_id(1L);
		//Create a file
		childFile = new MyFile("Dans mon ile","/root/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf",fileContent);
		childFile.setItem_id(1L);
		//create a child directory
		childDirectory = new Directory("dir", "/root/dir");
		childDirectory.setItem_id(2L);

	}

	@AfterEach
	void tearDown() throws Exception {
		this.myGroup = null;
		this.rootDir= null;
		this.member1= null;
		this.member2= null;
	}


	@Test
	void testGivenEmptyGroup_WhenConvertEntityToDTO_ThenReturnEmptyGroupDTO() {

		//When convert Entity to DTO
		GroupDTO groupDTO = groupDTOConversion.convertEntityToDTO(myGroup);

		//then return a group DTO
		//group_id
		assertNotNull(groupDTO.getGroup_id());
		assertEquals(myGroup.getRecipient_id(), Long.valueOf(groupDTO.getGroup_id()));
		//name
		assertNotNull(groupDTO.getName());
		assertEquals(myGroup.getName(),groupDTO.getName());
		//item_type
		assertNotNull(groupDTO.getItem_type());
		assertEquals(myGroup.getItem_type(),groupDTO.getItem_type());
		//members
		assertNotNull(groupDTO.getMembers());
		assertEquals(myGroup.getMembers().size(), groupDTO.getMembers().size());
	}

	@Test
	void testGivenGroupContainsOneMemberOwnsOneFileAndOneDirectory_WhenConvertEntityToDTO_ThenReturnGroupDTOContainsOneUserDTOOwnOneFileDTOAndOneDirectoryDTO() {

		//When add a member then convert Entity to DTO
		member1.getRoot().addChildren(childDirectory);
		member1.getRoot().addChildren(childFile);
		myGroup.addMember(member1);
		
		GroupDTO groupDTO = groupDTOConversion.convertEntityToDTO(myGroup);

		//then return a group DTO
		//group_id
		assertNotNull(groupDTO.getGroup_id());
		assertEquals(myGroup.getRecipient_id(), Long.valueOf(groupDTO.getGroup_id()));
		//name
		assertNotNull(groupDTO.getName());
		assertEquals(myGroup.getName(),groupDTO.getName());
		//item_type
		assertNotNull(groupDTO.getItem_type());
		assertEquals(myGroup.getItem_type(),groupDTO.getItem_type());
		//member1
		//user_id
		assertNotNull(groupDTO.getMembers().get(0).getRecipient_id());
		assertEquals(myGroup.getMembers().get(0).getRecipient_id(), Long.valueOf(groupDTO.getMembers().get(0).getRecipient_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getName());
		assertEquals(myGroup.getMembers().get(0).getName(), groupDTO.getMembers().get(0).getName());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getItem_type());
		assertEquals(myGroup.getMembers().get(0).getItem_type(), groupDTO.getMembers().get(0).getItem_type());
		//root
		assertNotNull(((MyUser) myGroup.getMembers().get(0)).getRoot());

	}

	@Test
	void testGivenGroupContainsOneMemberOwnsOneDirectoryContainsOneFile_WhenConvertEntityToDTO_ThenReturnGroupDTOContainsOneUserDTOOwnOneDirectoryDTOContainsOneFileDTO() {

		//When add a member then convert Entity to DTO
		((Directory) childDirectory).addChildren(childFile);
		member1.getRoot().addChildren(childDirectory);
		myGroup.addMember(member1);
		
		GroupDTO groupDTO = groupDTOConversion.convertEntityToDTO(myGroup);

		//then return a group DTO
		//group_id
		assertNotNull(groupDTO.getGroup_id());
		assertEquals(myGroup.getRecipient_id(), Long.valueOf(groupDTO.getGroup_id()));
		//name
		assertNotNull(groupDTO.getName());
		assertEquals(myGroup.getName(),groupDTO.getName());
		//item_type
		assertNotNull(groupDTO.getItem_type());
		assertEquals(myGroup.getItem_type(),groupDTO.getItem_type());
		
		//member1
		//user_id
		assertNotNull(groupDTO.getMembers().get(0).getRecipient_id());
		assertEquals(myGroup.getMembers().get(0).getRecipient_id(), Long.valueOf(groupDTO.getMembers().get(0).getRecipient_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getName());
		assertEquals(myGroup.getMembers().get(0).getName(), groupDTO.getMembers().get(0).getName());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getItem_type());
		assertEquals(myGroup.getMembers().get(0).getItem_type(), groupDTO.getMembers().get(0).getItem_type());
		//root
		assertNotNull(((MyUser) myGroup.getMembers().get(0)).getRoot());
}

}
