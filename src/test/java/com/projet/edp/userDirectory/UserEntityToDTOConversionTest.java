package com.projet.edp.userDirectory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.userDirectory.domain.MyUser;
import com.projet.edp.userDirectory.dto.UserDTO;
import com.projet.edp.userDirectory.dto.UserDTOConversion;

class UserEntityToDTOConversionTest extends UserDTOConversion {

	private static UserDTOConversion userDTOConversion ;


	private Directory rootDirectory;
	
	private MyUser user;

	private FileContent fileContent;

	private FileTreeItem childFile;

	private FileTreeItem childDirectory;

	@BeforeAll
	static void setup() {
		userDTOConversion = new UserDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		userDTOConversion = null;
	}


	@BeforeEach
	void setUp() throws Exception {
		//Given a root directory
		rootDirectory = new Directory("home", "/home");		
		rootDirectory.setItem_id(42L);
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
		//Given an user
		user = new MyUser("toto", "toto@me",rootDirectory);		
		user.setUser_id(1L);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.rootDirectory=null;
		this.fileContent=null;
		this.childFile=null;
		this.childDirectory=null;
		this.user= null;

	}

	@Test
	void testConvertUserToUserDTO_noChildren() {

		//When convert user to userDTO
		UserDTO userDTO = userDTOConversion.convertEntityToDTO(user);

		//Then return userDTO
		//user
		//user_id
		assertNotNull(userDTO.getUser_id());
		assertEquals(user.getUser_id(), Long.valueOf(userDTO.getUser_id()));
		//name
		assertNotNull(userDTO.getName());
		assertEquals(user.getName(), userDTO.getName());
		//mail
		assertNotNull(userDTO.getMail());
		assertEquals(user.getMail(), userDTO.getMail());
		//item_type
		assertNotNull(userDTO.getItem_type());
		assertEquals(user.getItem_type(), userDTO.getItem_type());
		//root
		assertNotNull(userDTO.getRoot());

		//root directory
		//item_id
		assertNotNull(userDTO.getRoot().getItem_id());
		assertEquals(user.getRoot().getItem_id(), Long.valueOf(userDTO.getRoot().getItem_id()));
		//name
		assertNotNull(userDTO.getRoot().getName());
		assertEquals(user.getRoot().getName(), userDTO.getRoot().getName());
		//item_local_path
		assertNotNull(userDTO.getRoot().getItem_local_path());
		assertEquals(user.getRoot().getItem_local_path(), userDTO.getRoot().getItem_local_path());
		//children
		assertNotNull(userDTO.getRoot().getChildren());
		assertEquals(user.getRoot().getChildren().size(), userDTO.getRoot().getChildren().size());
		//item_type
		assertNotNull(userDTO.getRoot().getItem_type());
		assertEquals(user.getRoot().getItem_type(), userDTO.getRoot().getItem_type());
	}



	@Test
	void testConvertUserToUserDTO_rootDirectoryContainsOneDirectoryAndOneFile() throws FileNotFoundException, IOException {
		//add children to root directory 
		user.getRoot().addChildren(childDirectory);
		user.getRoot().addChildren(childFile);

		//When convert user to userDTO
		UserDTO userDTO = userDTOConversion.convertEntityToDTO(user);

		//Then return userDTO
		//user_id
		assertNotNull(userDTO.getUser_id());
		assertEquals(user.getUser_id(), Long.valueOf(userDTO.getUser_id()));
		//name
		assertNotNull(userDTO.getName());
		assertEquals(user.getName(), userDTO.getName());
		//mail
		assertNotNull(userDTO.getMail());
		assertEquals(user.getMail(), userDTO.getMail());
		//item_type
		assertNotNull(userDTO.getItem_type());
		assertEquals(user.getItem_type(), userDTO.getItem_type());
		//root
		assertNotNull(userDTO.getRoot());

		//root directory
		//item_id
		assertNotNull(userDTO.getRoot().getItem_id());
		assertEquals(user.getRoot().getItem_id(), Long.valueOf(userDTO.getRoot().getItem_id()));
		//name
		assertNotNull(userDTO.getRoot().getName());
		assertEquals(user.getRoot().getName(), userDTO.getRoot().getName());
		//item_local_path
		assertNotNull(userDTO.getRoot().getItem_local_path());
		assertEquals(user.getRoot().getItem_local_path(), userDTO.getRoot().getItem_local_path());
		//children
		assertNotNull(userDTO.getRoot().getChildren());
		assertEquals(user.getRoot().getChildren().size(), userDTO.getRoot().getChildren().size());
		//item_type
		assertNotNull(userDTO.getRoot().getItem_type());
		assertEquals(user.getRoot().getItem_type(), userDTO.getRoot().getItem_type());
		
		//child directory
		//item_id
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_id());
		assertEquals(user.getRoot().getChildren().get(0).getItem_id(), Long.valueOf(userDTO.getRoot().getChildren().get(0).getItem_id()));
		//name
		assertNotNull(userDTO.getRoot().getChildren().get(0).getName());
		assertEquals(user.getRoot().getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getName());
		//item_local_path
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_local_path());
		assertEquals(user.getRoot().getChildren().get(0).getItem_local_path(), userDTO.getRoot().getChildren().get(0).getItem_local_path());
		//children
		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().size(), userDTO.getRoot().getChildren().get(0).getChildren().size());
		//item_type
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_type());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getItem_type(), userDTO.getRoot().getChildren().get(0).getItem_type());
		
		//file
		//item_id
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_id());
		assertEquals(user.getRoot().getChildren().get(0).getItem_id(), Long.valueOf(userDTO.getRoot().getChildren().get(0).getItem_id()));
		//item_name
		assertNotNull(userDTO.getRoot().getChildren().get(0).getName());
		assertEquals(user.getRoot().getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getName());
		//item_type
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_type());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getItem_type(), userDTO.getRoot().getChildren().get(0).getItem_type());
	}

	@Test
	void testConvertUserToUserDTO_childrenDirectoryContainsOneDirectoryContainsOneFile() throws FileNotFoundException, IOException {
		((Directory) childDirectory).addChildren(childFile);
		user.getRoot().addChildren(childDirectory);
		
		//When convert user to userDTO
		UserDTO userDTO = userDTOConversion.convertEntityToDTO(user);

		//Then return userDTO
		//user_id
		assertNotNull(userDTO.getUser_id());
		assertEquals(user.getUser_id(), Long.valueOf(userDTO.getUser_id()));
		//name
		assertNotNull(userDTO.getName());
		assertEquals(user.getName(), userDTO.getName());
		//mail
		assertNotNull(userDTO.getMail());
		assertEquals(user.getMail(), userDTO.getMail());
		//item_type
		assertNotNull(userDTO.getItem_type());
		assertEquals(user.getItem_type(), userDTO.getItem_type());
		//root
		assertNotNull(userDTO.getRoot());

		//root directory
		//item_id
		assertNotNull(userDTO.getRoot().getItem_id());
		assertEquals(user.getRoot().getItem_id(), Long.valueOf(userDTO.getRoot().getItem_id()));
		//name
		assertNotNull(userDTO.getRoot().getName());
		assertEquals(user.getRoot().getName(), userDTO.getRoot().getName());
		//item_local_path
		assertNotNull(userDTO.getRoot().getItem_local_path());
		assertEquals(user.getRoot().getItem_local_path(), userDTO.getRoot().getItem_local_path());
		//children
		assertNotNull(userDTO.getRoot().getChildren());
		assertEquals(user.getRoot().getChildren().size(), userDTO.getRoot().getChildren().size());
		//item_type
		assertNotNull(userDTO.getRoot().getItem_type());
		assertEquals(user.getRoot().getItem_type(), userDTO.getRoot().getItem_type());
		
		//child directory
		//item_id
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_id());
		assertEquals(user.getRoot().getChildren().get(0).getItem_id(), Long.valueOf(userDTO.getRoot().getChildren().get(0).getItem_id()));
		//name
		assertNotNull(userDTO.getRoot().getChildren().get(0).getName());
		assertEquals(user.getRoot().getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getName());
		//item_local_path
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_local_path());
		assertEquals(user.getRoot().getChildren().get(0).getItem_local_path(), userDTO.getRoot().getChildren().get(0).getItem_local_path());
		//children
		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().size(), userDTO.getRoot().getChildren().get(0).getChildren().size());
		//item_type
		assertNotNull(userDTO.getRoot().getChildren().get(0).getItem_type());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getItem_type(), userDTO.getRoot().getChildren().get(0).getItem_type());
		
		//file
		//item_id
		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getItem_id());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getItem_id(), Long.valueOf(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getItem_id()));
		//item_name
		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getName());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getChildren().get(0).getName());
		//item_type
		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getItem_type());
		assertEquals(((MyFile) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getItem_type(), userDTO.getRoot().getChildren().get(0).getChildren().get(0).getItem_type());

	}
}
