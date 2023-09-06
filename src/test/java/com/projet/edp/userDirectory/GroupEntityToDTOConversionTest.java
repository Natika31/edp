package com.projet.edp.userDirectory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.userDirectory.domain.MyGroup;
import com.projet.edp.userDirectory.domain.MyUser;
import com.projet.edp.userDirectory.dto.GroupDTO;
import com.projet.edp.userDirectory.dto.GroupDTOConversion;

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
		myGroup.setGroup_id(1L);
		//Given a root directory
		rootDir = new Directory("home","/home");
		rootDir.setItem_id(1L);
		//Given member 1 and its empty root directory
		member1 = new MyUser("Natacha", "natacha@mail.me", rootDir);
		member1.setUser_id(1L);
		//Given member 2 and its empty root directory
		member2 = new MyUser("Robert", "robert@mail.me", rootDir);
		member2.setUser_id(2L);
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
		assertEquals(myGroup.getGroup_id(), Long.valueOf(groupDTO.getGroup_id()));
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
		assertEquals(myGroup.getGroup_id(), Long.valueOf(groupDTO.getGroup_id()));
		//name
		assertNotNull(groupDTO.getName());
		assertEquals(myGroup.getName(),groupDTO.getName());
		//item_type
		assertNotNull(groupDTO.getItem_type());
		assertEquals(myGroup.getItem_type(),groupDTO.getItem_type());
		//member1
		//user_id
		assertNotNull(groupDTO.getMembers().get(0).getUser_id());
		assertEquals(myGroup.getMembers().get(0).getUser_id(), Long.valueOf(groupDTO.getMembers().get(0).getUser_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getName());
		assertEquals(myGroup.getMembers().get(0).getName(), groupDTO.getMembers().get(0).getName());
		//mail
		assertNotNull(groupDTO.getMembers().get(0).getMail());
		assertEquals(myGroup.getMembers().get(0).getMail(), groupDTO.getMembers().get(0).getMail());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getItem_type());
		assertEquals(myGroup.getMembers().get(0).getItem_type(), groupDTO.getMembers().get(0).getItem_type());
		//root
		assertNotNull(myGroup.getMembers().get(0).getRoot());

		//root directory
		//item_id
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getItem_id());
		assertEquals(myGroup.getMembers().get(0).getRoot().getItem_id(), Long.valueOf(groupDTO.getMembers().get(0).getRoot().getItem_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getName());
		assertEquals(myGroup.getMembers().get(0).getRoot().getName(), groupDTO.getMembers().get(0).getRoot().getName());
		//item_local_path
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getItem_local_path());
		assertEquals(myGroup.getMembers().get(0).getRoot().getItem_local_path(), groupDTO.getMembers().get(0).getRoot().getItem_local_path());
		//children
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().size(), groupDTO.getMembers().get(0).getRoot().getChildren().size());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getItem_type());
		assertEquals(myGroup.getMembers().get(0).getRoot().getItem_type(), groupDTO.getMembers().get(0).getRoot().getItem_type());
		
		//child directory
		//item_id
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_id());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(0).getItem_id(), Long.valueOf(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getName());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(0).getName(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getName());
		//item_local_path
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_local_path());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(0).getItem_local_path(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_local_path());
		//children
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren());
		assertEquals(((Directory) myGroup.getMembers().get(0).getRoot().getChildren().get(0)).getChildren().size(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().size());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_type());
		assertEquals(((Directory) myGroup.getMembers().get(0).getRoot().getChildren().get(0)).getItem_type(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_type());
		
		//file
		//item_id
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(1).getItem_id());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(1).getItem_id(), Long.valueOf(groupDTO.getMembers().get(0).getRoot().getChildren().get(1).getItem_id()));
		//item_name
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(1).getName());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(1).getName(), groupDTO.getMembers().get(0).getRoot().getChildren().get(1).getName());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(1).getItem_type());
		assertEquals(((MyFile) myGroup.getMembers().get(0).getRoot().getChildren().get(1)).getItem_type(), groupDTO.getMembers().get(0).getRoot().getChildren().get(1).getItem_type());	
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
		assertEquals(myGroup.getGroup_id(), Long.valueOf(groupDTO.getGroup_id()));
		//name
		assertNotNull(groupDTO.getName());
		assertEquals(myGroup.getName(),groupDTO.getName());
		//item_type
		assertNotNull(groupDTO.getItem_type());
		assertEquals(myGroup.getItem_type(),groupDTO.getItem_type());
		
		//member1
		//user_id
		assertNotNull(groupDTO.getMembers().get(0).getUser_id());
		assertEquals(myGroup.getMembers().get(0).getUser_id(), Long.valueOf(groupDTO.getMembers().get(0).getUser_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getName());
		assertEquals(myGroup.getMembers().get(0).getName(), groupDTO.getMembers().get(0).getName());
		//mail
		assertNotNull(groupDTO.getMembers().get(0).getMail());
		assertEquals(myGroup.getMembers().get(0).getMail(), groupDTO.getMembers().get(0).getMail());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getItem_type());
		assertEquals(myGroup.getMembers().get(0).getItem_type(), groupDTO.getMembers().get(0).getItem_type());
		//root
		assertNotNull(myGroup.getMembers().get(0).getRoot());

		//root directory
		//item_id
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getItem_id());
		assertEquals(myGroup.getMembers().get(0).getRoot().getItem_id(), Long.valueOf(groupDTO.getMembers().get(0).getRoot().getItem_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getName());
		assertEquals(myGroup.getMembers().get(0).getRoot().getName(), groupDTO.getMembers().get(0).getRoot().getName());
		//item_local_path
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getItem_local_path());
		assertEquals(myGroup.getMembers().get(0).getRoot().getItem_local_path(), groupDTO.getMembers().get(0).getRoot().getItem_local_path());
		//children
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().size(), groupDTO.getMembers().get(0).getRoot().getChildren().size());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getItem_type());
		assertEquals(myGroup.getMembers().get(0).getRoot().getItem_type(), groupDTO.getMembers().get(0).getRoot().getItem_type());
		
		//child directory
		//item_id
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_id());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(0).getItem_id(), Long.valueOf(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_id()));
		//name
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getName());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(0).getName(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getName());
		//item_local_path
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_local_path());
		assertEquals(myGroup.getMembers().get(0).getRoot().getChildren().get(0).getItem_local_path(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_local_path());
		//children
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren());
		assertEquals(((Directory) myGroup.getMembers().get(0).getRoot().getChildren().get(0)).getChildren().size(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().size());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_type());
		assertEquals(((Directory) myGroup.getMembers().get(0).getRoot().getChildren().get(0)).getItem_type(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getItem_type());
		
		//file
		//item_id
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().get(0).getItem_id());
		assertEquals(((Directory) myGroup.getMembers().get(0).getRoot().getChildren().get(0)).getChildren().get(0).getItem_id(), Long.valueOf(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().get(0).getItem_id()));
		//item_name
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().get(0).getName());
		assertEquals(((Directory) myGroup.getMembers().get(0).getRoot().getChildren().get(0)).getChildren().get(0).getName(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().get(0).getName());
		//item_type
		assertNotNull(groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().get(0).getItem_type());
		assertEquals(((MyFile) ((Directory) myGroup.getMembers().get(0).getRoot().getChildren().get(0)).getChildren().get(0)).getItem_type(), groupDTO.getMembers().get(0).getRoot().getChildren().get(0).getChildren().get(0).getItem_type());	
	}

}
