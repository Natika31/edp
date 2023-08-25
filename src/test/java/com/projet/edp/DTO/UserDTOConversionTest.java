package com.projet.edp.DTO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileContent;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.fileTree.dto.TreeItemDTO;
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
		//Given an user
		User user = new User("name", "email");
		user.setUser_id(1L);
		user.getRoot().setItem_id(42L);
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
		//Given an user
		User user = new User("name", "email");
		user.setUser_id(1L);
		user.getRoot().setItem_id(42L);
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
		//Given an user dto
		UserDTO userDTO = new UserDTO("3", "toto", "toto@me", "user");
		userDTO.getRoot().setItem_id("42");

		//When convert user DTO to user item
		User user = userDTOConversion.convertDTOtoEntities(userDTO); 

		//Then Return user 
		assertNotNull(user.getUser_id());
		assertEquals(Long.valueOf(userDTO.getUser_id()), user.getUser_id());
		assertNotNull(user.getName());
		assertEquals(userDTO.getName(), user.getName());
		assertNotNull(user.getItem_type());
		assertEquals(User.class.toString(), user.getClass().toString());
		assertNotNull(user.getRoot());
		assertEquals(userDTO.getRoot().getItem_id(), String.valueOf(user.getRoot().getItem_id()) );
		assertEquals(userDTO.getRoot().getName(), user.getRoot().getName());
		assertEquals(userDTO.getRoot().getItem_local_path(), user.getRoot().getItem_local_path());
		assertNotNull(user.getRoot().getChildren());
		assertEquals(userDTO.getRoot().getChildren().size(), user.getRoot().getChildren().size());
	}

	@Test
	void testConvertUserDTOtoUser_childrenDirectoryContainsOneDirectoryAndOneFile() {

		//Given two items DTO
		TreeItemDTO parentItemDTO = new TreeItemDTO("1","dir1","/home/dir1","folder");
		TreeItemDTO childDItemDTO = new TreeItemDTO("2","dir2","/home/dir1/dir2",  "folder");
		//Given a file item DTO
		TreeItemDTO fItemDTO = new TreeItemDTO("3","filename","/home/dir1/filename.pdf",  "file");
		//add a child to parent directory's children list
		List<TreeItemDTO> children = new ArrayList<>();
		children.add(childDItemDTO);
		children.add(fItemDTO);
		parentItemDTO.setChildren(children);
		//Given an user dto
		UserDTO userDTO = new UserDTO("3", "toto", "toto@me", "user");
		userDTO.getRoot().setItem_id("42");
		userDTO.getRoot().addChildrenDTO(parentItemDTO);

		//When convert user DTO to user
		User user = userDTOConversion.convertDTOtoEntities(userDTO); 

		//Then Return user
		assertNotNull(user.getUser_id());
		assertEquals(Long.valueOf(userDTO.getUser_id()), user.getUser_id());
		assertNotNull(user.getName());
		assertEquals(userDTO.getName(), user.getName());
		assertNotNull(user.getItem_type());
		assertEquals(User.class.toString(), user.getClass().toString());

		assertNotNull(user.getRoot());
		assertNotNull(user.getRoot().getItem_id());
		assertEquals(userDTO.getRoot().getItem_id(), String.valueOf(user.getRoot().getItem_id()) );
		assertNotNull(user.getRoot().getName());

		//home
		assertEquals("home", user.getRoot().getName());
		assertEquals(userDTO.getRoot().getItem_local_path(), user.getRoot().getItem_local_path());
		assertEquals(Directory.class.toString(), user.getRoot().getClass().toString());
		assertNotNull(user.getRoot().getChildren());
		assertEquals(userDTO.getRoot().getChildren().size(), user.getRoot().getChildren().size());	

		//parent
		assertEquals(userDTO.getRoot().getChildren().get(0).getItem_id(),String.valueOf(((Directory) user.getRoot()).getChildren().get(0).getItem_id()));
		assertNotNull(((Directory) user.getRoot()).getChildren().get(0).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getName(), ((Directory) user.getRoot()).getChildren().get(0).getName());
		assertEquals( Directory.class.toString(),((Directory) user.getRoot()).getChildren().get(0).getClass().toString());
		assertNotNull(((Directory)((Directory) user.getRoot()).getChildren().get(0)).getChildren());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().size(), ((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().size());

		//directory child 1
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getItem_id(),String.valueOf(((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(0).getItem_id()));
		assertNotNull(((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(0).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getName(), ((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(0).getName());
		assertEquals( Directory.class.toString(),((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(0).getClass().toString());
		assertNotNull(((Directory) ((Directory)((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(0)).getChildren());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().size(), ((Directory) ((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(0)).getChildren().size());

		//file child 2
		assertNotNull(((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(1).getItem_id());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(1).getItem_id(),String.valueOf(((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(1).getItem_id()));
		assertNotNull(((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(1).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(1).getName(),((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(1).getName());
		assertEquals(MyFile.class.toString(),((Directory) ((Directory) user.getRoot()).getChildren().get(0)).getChildren().get(1).getClass().toString());

	}
	
	@Test
	void testConvertUserDTOtoUser_childrenDirectoryContainsOneDirectoryContainsOneFile() {
		//Given two items DTO
		TreeItemDTO parentItemDTOId1 = new TreeItemDTO("1","dir1","/home/dir1","folder");
		TreeItemDTO childDItemDTOId2 = new TreeItemDTO("2","dir2","/home/dir1/dir2","folder");
		//Given a file item DTO
		TreeItemDTO fItemDTOId3 = new TreeItemDTO("3","filename","/home/dir1/dir2/filename.pdf","file");
		//add a child to child directory's children list
		List<TreeItemDTO> children2 = new ArrayList<>();
		children2.add(fItemDTOId3);
		childDItemDTOId2.setChildren(children2);
		//add a child to parent directory's children list
		List<TreeItemDTO> children1 = new ArrayList<>();
		children1.add(childDItemDTOId2);
		parentItemDTOId1.setChildren(children1);
		//Given an user dto
		UserDTO userDTO = new UserDTO("3", "toto", "toto@me", "user");
		userDTO.getRoot().setItem_id("42");
		userDTO.getRoot().addChildrenDTO(parentItemDTOId1);
		
		User user = userDTOConversion.convertDTOtoEntities(userDTO); 

		//Then Return user
		assertNotNull(user.getUser_id());
		assertEquals(Long.valueOf(userDTO.getUser_id()), user.getUser_id());
		assertNotNull(user.getName());
		assertEquals(userDTO.getName(), user.getName());
		assertNotNull(user.getItem_type());
		assertEquals(User.class.toString(), user.getClass().toString());

		assertNotNull(user.getRoot());
		assertNotNull(user.getRoot().getItem_id());
		assertEquals(userDTO.getRoot().getItem_id(), String.valueOf(user.getRoot().getItem_id()) );
		assertNotNull(user.getRoot().getName());
		
		//home
		assertEquals("home", user.getRoot().getName());
		assertEquals(userDTO.getRoot().getItem_local_path(), user.getRoot().getItem_local_path());
		assertEquals(Directory.class.toString(), user.getRoot().getClass().toString());
		assertNotNull(user.getRoot().getChildren());
		assertEquals(userDTO.getRoot().getChildren().size(), user.getRoot().getChildren().size());	
		
		//parent
		assertNotNull(user.getRoot().getChildren().get(0).getItem_id());
		assertNotNull(user.getRoot().getChildren().get(0).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getItem_id(), String.valueOf(user.getRoot().getChildren().get(0).getItem_id()));
		assertNotNull(user.getRoot().getChildren().get(0).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getName(),user.getRoot().getChildren().get(0).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getItem_local_path(), user.getRoot().getChildren().get(0).getItem_local_path());
		assertEquals(Directory.class.toString(),user.getRoot().getChildren().get(0).getClass().toString());
		
		//parent's child
		assertNotNull(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getItem_id());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getItem_id(),  String.valueOf(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getItem_id()));
		assertNotNull(((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getName(),((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getName());
		assertEquals(Directory.class.toString(), ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0).getClass().toString());
		assertNotNull(((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().size(), ((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().size());

		//child's child
		assertNotNull(((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(0).getItem_id());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getItem_id(),String.valueOf(((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(0).getItem_id()));
		assertNotNull(((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(0).getName());
		assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getName(),((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(0).getName());
		assertEquals(MyFile.class.toString(),((Directory) ((Directory) user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(0).getClass().toString());
	}
}
