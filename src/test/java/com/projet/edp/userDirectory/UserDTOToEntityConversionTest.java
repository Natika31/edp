package com.projet.edp.userDirectory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.dto.TreeItemDTO;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.userDirectory.domain.MyGroup;
import com.projet.edp.userDirectory.domain.MyUser;
import com.projet.edp.userDirectory.dto.GroupDTO;
import com.projet.edp.userDirectory.dto.UserDTO;
import com.projet.edp.userDirectory.dto.UserDTOConversion;

class UserDTOToEntityConversionTest extends UserDTOConversion {

	private static UserDTOConversion userDTOConversion ;

	@BeforeAll
	static void setup() {
		userDTOConversion = new UserDTOConversion();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		userDTOConversion = null;
	}

	private Directory rootDirectory;
	private MyUser user;
	
	@BeforeEach
	void setUp() throws Exception {
		//Given a root directory
		rootDirectory = new Directory("home", "/home");		
		rootDirectory.setItem_id(42L);
		//Given an user
		user = new MyUser("toto", "toto@me",rootDirectory);		
		user.setUser_id(1L);
	}

	@AfterEach
	void tearDown() throws Exception {
		rootDirectory=null;
		user= null;
	}

	/*
	 * @Test void testConvertUserDTOtoUser_noChildren() { //Given an user dto
	 * UserDTO userDTO = new UserDTO("3", "toto", "toto@me");
	 * userDTO.getRoot().setItem_id("42");
	 * 
	 * //When convert user DTO to user item MyUser user =
	 * userDTOConversion.convertDTOtoEntities(userDTO);
	 * 
	 * //Then Return user assertNotNull(user.getUser_id());
	 * assertEquals(Long.valueOf(userDTO.getUser_id()), user.getUser_id());
	 * assertNotNull(user.getName()); assertEquals(userDTO.getName(),
	 * user.getName()); assertNotNull(user.getItem_type());
	 * assertEquals(MyUser.class.toString(), user.getClass().toString());
	 * assertNotNull(user.getRoot()); assertEquals(userDTO.getRoot().getItem_id(),
	 * String.valueOf(user.getRoot().getItem_id()) );
	 * assertEquals(userDTO.getRoot().getName(), user.getRoot().getName());
	 * assertEquals(userDTO.getRoot().getItem_local_path(),
	 * user.getRoot().getItem_local_path());
	 * assertNotNull(user.getRoot().getChildren());
	 * assertEquals(userDTO.getRoot().getChildren().size(),
	 * user.getRoot().getChildren().size()); }
	 * 
	 * @Test void
	 * testConvertUserDTOtoUser_childrenDirectoryContainsOneDirectoryAndOneFile() {
	 * 
	 * //Given two items DTO TreeItemDTO parentItemDTO = new
	 * TreeItemDTO("1","dir1","/home/dir1","folder"); TreeItemDTO childDItemDTO =
	 * new TreeItemDTO("2","dir2","/home/dir1/dir2", "folder"); //Given a file item
	 * DTO TreeItemDTO fItemDTO = new
	 * TreeItemDTO("3","filename","/home/dir1/filename.pdf", "file"); //add a child
	 * to parent directory's children list List<TreeItemDTO> children = new
	 * ArrayList<>(); children.add(childDItemDTO); children.add(fItemDTO);
	 * parentItemDTO.setChildren(children); //Given an user dto UserDTO userDTO =
	 * new UserDTO("3", "toto", "toto@me"); userDTO.getRoot().setItem_id("42");
	 * userDTO.getRoot().addChildrenDTO(parentItemDTO);
	 * 
	 * //When convert user DTO to user MyUser user =
	 * userDTOConversion.convertDTOtoEntities(userDTO);
	 * 
	 * //Then Return user assertNotNull(user.getUser_id());
	 * assertEquals(Long.valueOf(userDTO.getUser_id()), user.getUser_id());
	 * assertNotNull(user.getName()); assertEquals(userDTO.getName(),
	 * user.getName()); assertNotNull(user.getItem_type());
	 * assertEquals(MyUser.class.toString(), user.getClass().toString());
	 * 
	 * assertNotNull(user.getRoot()); assertNotNull(user.getRoot().getItem_id());
	 * assertEquals(userDTO.getRoot().getItem_id(),
	 * String.valueOf(user.getRoot().getItem_id()) );
	 * assertNotNull(user.getRoot().getName());
	 * 
	 * //home assertEquals("home", user.getRoot().getName());
	 * assertEquals(userDTO.getRoot().getItem_local_path(),
	 * user.getRoot().getItem_local_path());
	 * assertEquals(Directory.class.toString(),
	 * user.getRoot().getClass().toString());
	 * assertNotNull(user.getRoot().getChildren());
	 * assertEquals(userDTO.getRoot().getChildren().size(),
	 * user.getRoot().getChildren().size());
	 * 
	 * //parent
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getItem_id(),String.
	 * valueOf(((Directory) user.getRoot()).getChildren().get(0).getItem_id()));
	 * assertNotNull(((Directory) user.getRoot()).getChildren().get(0).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getName(), ((Directory)
	 * user.getRoot()).getChildren().get(0).getName()); assertEquals(
	 * Directory.class.toString(),((Directory)
	 * user.getRoot()).getChildren().get(0).getClass().toString());
	 * assertNotNull(((Directory)((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().size(),
	 * ((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().size());
	 * 
	 * //directory child 1
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getItem_id(),String.valueOf(((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(0).getItem_id()));
	 * assertNotNull(((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(0).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getName(), ((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(0).getName());
	 * assertEquals( Directory.class.toString(),((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(0).getClass().
	 * toString()); assertNotNull(((Directory) ((Directory)((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(0)).getChildren());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getChildren().size(), ((Directory) ((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(0)).getChildren().
	 * size());
	 * 
	 * //file child 2 assertNotNull(((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(1).getItem_id());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(1).
	 * getItem_id(),String.valueOf(((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(1).getItem_id()));
	 * assertNotNull(((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(1).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(1).
	 * getName(),((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(1).getName());
	 * assertEquals(MyFile.class.toString(),((Directory) ((Directory)
	 * user.getRoot()).getChildren().get(0)).getChildren().get(1).getClass().
	 * toString());
	 * 
	 * }
	 * 
	 * @Test void
	 * testConvertUserDTOtoUser_childrenDirectoryContainsOneDirectoryContainsOneFile
	 * () { //Given two items DTO TreeItemDTO parentItemDTOId1 = new
	 * TreeItemDTO("1","dir1","/home/dir1","folder"); TreeItemDTO childDItemDTOId2 =
	 * new TreeItemDTO("2","dir2","/home/dir1/dir2","folder"); //Given a file item
	 * DTO TreeItemDTO fItemDTOId3 = new
	 * TreeItemDTO("3","filename","/home/dir1/dir2/filename.pdf","file"); //add a
	 * child to child directory's children list List<TreeItemDTO> children2 = new
	 * ArrayList<>(); children2.add(fItemDTOId3);
	 * childDItemDTOId2.setChildren(children2); //add a child to parent directory's
	 * children list List<TreeItemDTO> children1 = new ArrayList<>();
	 * children1.add(childDItemDTOId2); parentItemDTOId1.setChildren(children1);
	 * //Given an user dto UserDTO userDTO = new UserDTO("3", "toto", "toto@me");
	 * userDTO.getRoot().setItem_id("42");
	 * userDTO.getRoot().addChildrenDTO(parentItemDTOId1);
	 * 
	 * MyUser user = userDTOConversion.convertDTOtoEntities(userDTO);
	 * 
	 * //Then Return user assertNotNull(user.getUser_id());
	 * assertEquals(Long.valueOf(userDTO.getUser_id()), user.getUser_id());
	 * assertNotNull(user.getName()); assertEquals(userDTO.getName(),
	 * user.getName()); assertNotNull(user.getItem_type());
	 * assertEquals(MyUser.class.toString(), user.getClass().toString());
	 * 
	 * assertNotNull(user.getRoot()); assertNotNull(user.getRoot().getItem_id());
	 * assertEquals(userDTO.getRoot().getItem_id(),
	 * String.valueOf(user.getRoot().getItem_id()) );
	 * assertNotNull(user.getRoot().getName());
	 * 
	 * //home assertEquals("home", user.getRoot().getName());
	 * assertEquals(userDTO.getRoot().getItem_local_path(),
	 * user.getRoot().getItem_local_path());
	 * assertEquals(Directory.class.toString(),
	 * user.getRoot().getClass().toString());
	 * assertNotNull(user.getRoot().getChildren());
	 * assertEquals(userDTO.getRoot().getChildren().size(),
	 * user.getRoot().getChildren().size());
	 * 
	 * //parent assertNotNull(user.getRoot().getChildren().get(0).getItem_id());
	 * assertNotNull(user.getRoot().getChildren().get(0).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getItem_id(),
	 * String.valueOf(user.getRoot().getChildren().get(0).getItem_id()));
	 * assertNotNull(user.getRoot().getChildren().get(0).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getName(),user.getRoot().
	 * getChildren().get(0).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getItem_local_path(),
	 * user.getRoot().getChildren().get(0).getItem_local_path());
	 * assertEquals(Directory.class.toString(),user.getRoot().getChildren().get(0).
	 * getClass().toString());
	 * 
	 * //parent's child assertNotNull(((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0).getItem_id());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getItem_id(), String.valueOf(((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0).getItem_id()));
	 * assertNotNull(((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getName(),((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0).getName());
	 * assertEquals(Directory.class.toString(), ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0).getClass().toString
	 * ()); assertNotNull(((Directory) ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getChildren().size(), ((Directory) ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().size
	 * ());
	 * 
	 * //child's child assertNotNull(((Directory) ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(
	 * 0).getItem_id());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getChildren().get(0).getItem_id(),String.valueOf(((Directory) ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(
	 * 0).getItem_id())); assertNotNull(((Directory) ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(
	 * 0).getName());
	 * assertEquals(userDTO.getRoot().getChildren().get(0).getChildren().get(0).
	 * getChildren().get(0).getName(),((Directory) ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(
	 * 0).getName()); assertEquals(MyFile.class.toString(),((Directory) ((Directory)
	 * user.getRoot().getChildren().get(0)).getChildren().get(0)).getChildren().get(
	 * 0).getClass().toString()); }
	 */
}
