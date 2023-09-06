package com.projet.edp.userDirectory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.userDirectory.domain.MyGroup;
import com.projet.edp.userDirectory.domain.MyUser;
import com.projet.edp.userDirectory.dto.GroupDTO;
import com.projet.edp.userDirectory.dto.GroupDTOConversion;
import com.projet.edp.userDirectory.dto.UserDTO;

class GroupDTOToEntityConversionTest {

	private static GroupDTOConversion groupDTOConversion;

	private MyGroup myGroup;

	private MyUser member1;

	private Directory rootDir;

	private GroupDTO groupDTO;

	private UserDTO memberDTO;




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
		member1 = new MyUser("Natacha", "natacha@mail.me", (Directory) rootDir);
		member1.setUser_id(1L);

		//Given an empty groupDTO
		groupDTO =  new GroupDTO("1", "ma famille");
		//Given member 1 and its empty root directory
		memberDTO = new UserDTO("1", "Natacha", "natacha@mail.me");
	}

	@AfterEach
	void tearDown() throws Exception {
		myGroup = null;
	}

	@Test
	void testConvertDTOtoEntities_GivenEmptyGroupDTO_WhenConvertEntityToDTO_ThenReturnGroup() {
		//When convert DTO to entity
		MyGroup myGroup = groupDTOConversion.convertDTOtoEntities(groupDTO);


		//then return an empty a group 
		//group
		assertNotNull(myGroup.getGroup_id());
		assertEquals(groupDTO.getGroup_id(), String.valueOf(myGroup.getGroup_id()));
		assertNotNull(myGroup.getName());
		assertEquals(groupDTO.getName(), myGroup.getName());
		assertEquals(MyGroup.class.toString(), myGroup.getClass().toString());
		assertEquals(groupDTO.getMembers().size(), myGroup.getMembers().size());
	}

	/*
	 * @Test void
	 * testConvertDTOtoEntities_GivenGroupDTOContainsOneMemberDTO_WhenConvertEntityToDTO_ThenReturnGroupContainsOneUser
	 * () { //When convert DTO to entity groupDTO.addMember(memberDTO); MyGroup
	 * myGroup = groupDTOConversion.convertDTOtoEntities(groupDTO);
	 * 
	 * //then return a group and members //group
	 * assertNotNull(myGroup.getGroup_id()); assertEquals(groupDTO.getGroup_id(),
	 * String.valueOf(myGroup.getGroup_id())); assertNotNull(myGroup.getName());
	 * assertEquals(groupDTO.getName(), myGroup.getName());
	 * assertEquals(MyGroup.class.toString(), myGroup.getClass().toString());
	 * assertEquals(groupDTO.getMembers().size(), myGroup.getMembers().size());
	 * //member assertNotNull(myGroup.getMembers().get(0).getUser_id());
	 * assertEquals(groupDTO.getMembers().get(0).getUser_id(),
	 * String.valueOf(myGroup.getMembers().get(0).getUser_id()));
	 * assertNotNull(myGroup.getMembers().get(0).getName());
	 * assertEquals(groupDTO.getMembers().get(0).getName(),myGroup.getMembers().get(
	 * 0).getName()); //TODO: assertEquals(MyUser.class.toString() ,
	 * group.getMembers().get(0).getItem_type()); //root directory
	 * assertNotNull(myGroup.getMembers().get(0).getRoot()); //TODO:
	 * assertEquals(groupDTO.getMembers().get(0).getRoot().getItem_id(),
	 * String.valueOf(group.getMembers().get(0).getRoot().getItem_id()));
	 * assertNotNull(myGroup.getMembers().get(0).getRoot().getName());
	 * assertEquals(groupDTO.getMembers().get(0).getRoot().getName(),myGroup.
	 * getMembers().get(0).getRoot().getName()); //TODO:
	 * assertEquals(Directory.class.toString() ,
	 * group.getMembers().get(0).getRoot().getItem_type());
	 * 
	 * }
	 */


}
