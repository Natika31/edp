package com.projet.edp.DTO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileContent;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.userDirectory.domain.User;
import com.projet.edp.userDirectory.dto.UserDTO;
import com.projet.edp.userDirectory.dto.UserDTOConversion;

class UserDTOConversionTest extends UserDTOConversion {

	private static UserDTOConversion userDTOConversion ;

	@BeforeAll
	static void setup() {
		userDTOConversion = new UserDTOConversion();
	}

	@Test
	void testConvertUserToUserDTO_noChildren() {
		//Given an user
		User user = new User("name", "email");
		user.setUser_id(1L);
		user.getRoot().setItem_id(42L);

		//When convert user to userDTO
		UserDTO userDTO = userDTOConversion.convertEntityToDTO(user);

		//Then return userDTO
		assertEquals(user.getUser_id(), Long.valueOf(userDTO.getUser_id()));
		assertEquals(user.getName(), userDTO.getName());
		assertEquals(user.getMail(), userDTO.getMail());
		assertEquals("user",userDTO.getItem_type());
		assertNotNull(userDTO.getRoot());
		assertEquals(user.getRoot().getItem_id(), Long.valueOf(userDTO.getRoot().getItem_id()) );
		assertEquals(user.getRoot().getName(), userDTO.getRoot().getName());
		assertEquals(user.getRoot().getItem_local_path(), userDTO.getRoot().getItem_local_path());
		assertNotNull(userDTO.getRoot().getChildren());
		assertEquals(user.getRoot().getChildren().size(), userDTO.getRoot().getChildren().size());
	}

	@Test
	void testConvertUserToUserDTO_childrenDirectoryContainsOneDirectoryAndOneFile() throws FileNotFoundException, IOException {
		//Given an user
		User user = new User("name", "email");
		user.setUser_id(1L);
		user.getRoot().setItem_id(42L);

		//Given two directory items
		FileTreeItem dItem1 = new Directory("dir1","/home/dir1");
		dItem1.setItem_id(43L);
		FileTreeItem dItem2 = new Directory("dir2","/home/dir1/dir2");
		dItem2.setItem_id(44L);
		//Given a file item
		FileContent fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		FileTreeItem fItem = new MyFile("file", "/home/dir1/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent ); 
		fItem.setItem_id(42L);
		//add a child to parent directory's children list
		Directory parentDir = (Directory) dItem1;
		parentDir.addChildren(dItem2);
		parentDir.addChildren(fItem);
		user.getRoot().addChildren(parentDir);

		//When convert user to userDTO
		UserDTO userDTO = userDTOConversion.convertEntityToDTO(user);

		//Then return userDTO
		assertEquals(user.getUser_id(), Long.valueOf(userDTO.getUser_id()));
		assertEquals(user.getName(), userDTO.getName());
		assertEquals(user.getMail(), userDTO.getMail());
		assertEquals("user",userDTO.getItem_type());
		assertNotNull(userDTO.getRoot());
		assertEquals(user.getRoot().getItem_id(), Long.valueOf(userDTO.getRoot().getItem_id()) );
		assertEquals(user.getRoot().getName(), userDTO.getRoot().getName());
		assertEquals(user.getRoot().getItem_local_path(), userDTO.getRoot().getItem_local_path());
		assertNotNull(userDTO.getRoot().getChildren());
		assertNotEquals(0, userDTO.getRoot().getChildren().size());
		assertEquals(user.getRoot().getChildren().size(), userDTO.getRoot().getChildren().size());
		assertEquals(user.getRoot().getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getName());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getChildren().get(0).getName());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(1).getName(), userDTO.getRoot().getChildren().get(0).getChildren().get(1).getName());
	}
	
	@Test
	void testConvertUserToUserDTO_childrenDirectoryContainsOneDirectoryContainsOneFile() throws FileNotFoundException, IOException {
		//Given an user
		User user = new User("name", "email");
		user.setUser_id(1L);
		user.getRoot().setItem_id(42L);

		//Given two directory items
		FileTreeItem dItem1 = new Directory("dir1","/home/dir1");
		dItem1.setItem_id(43L);
		FileTreeItem dItem2 = new Directory("dir2","/home/dir1/dir2");
		dItem2.setItem_id(44L);
		//Given a file item
		FileContent fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		FileTreeItem fItem = new MyFile("file", "/home/dir1/dir2/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent ); 
		fItem.setItem_id(45L);
		//add a child to child directory's children list
		Directory childDir = (Directory) dItem2;
		childDir.addChildren(fItem);
		//add a child to parent directory's children list
		Directory parentDir = (Directory) dItem1;
		parentDir.addChildren(dItem2);
		user.getRoot().addChildren(parentDir);

		//When convert user to userDTO
		UserDTO userDTO = userDTOConversion.convertEntityToDTO(user);

		//Then return userDTO
		assertEquals(user.getUser_id(), Long.valueOf(userDTO.getUser_id()));
		assertEquals(user.getName(), userDTO.getName());
		assertEquals(user.getMail(), userDTO.getMail());
		assertEquals("user",userDTO.getItem_type());
		assertNotNull(userDTO.getRoot());
		assertEquals(user.getRoot().getItem_id(), Long.valueOf(userDTO.getRoot().getItem_id()) );
		assertEquals(user.getRoot().getName(), userDTO.getRoot().getName());
		assertEquals(user.getRoot().getItem_local_path(), userDTO.getRoot().getItem_local_path());
		assertNotNull(userDTO.getRoot().getChildren());
		assertNotEquals(0, userDTO.getRoot().getChildren().size());
		assertEquals(user.getRoot().getChildren().size(), userDTO.getRoot().getChildren().size());
		assertEquals(user.getRoot().getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getName());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getName(), userDTO.getRoot().getChildren().get(0).getChildren().get(0).getName());

		assertNotNull(((Directory) user.getRoot().getChildren().get(0)).getChildren());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().size(), userDTO.getRoot().getChildren().get(0).getChildren().size());
		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren().get(0));
		assertNotNull(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getItem_id());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getItem_id(),Long.valueOf(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getItem_id()));
		assertNotNull(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getName());
		assertEquals(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getName(),userDTO.getRoot().getChildren().get(0).getChildren().get(0).getName());

		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getName());
		assertNotNull(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getItem_id());
		assertEquals(((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(0).getItem_id(),Long.valueOf(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getItem_id()));
		assertEquals(((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(0).getName(),userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getName());
		assertEquals("file", userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getItem_type());

	}

	@Test
	void testConvertUserDTOtoUser_noChildren() {
		fail("Not yet implemented");
	}
	
	@Test
	void testConvertUserDTOtoUser_childrenDirectoryContainsOneDirectoryAndOneFile() {
		fail("Not yet implemented");
	}

}
