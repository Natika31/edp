package com.projet.edp.directoryViewerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.dto.TreeDTOConversion;
import com.projet.edp.fileTree.dto.TreeItemDTO;
import com.projet.edp.fileViewer.domain.MyFile;

class TreeDTOConversionDTOToEntityTest {

	private static TreeDTOConversion treeItemDTOConversion ;

	private TreeItemDTO fItemDTO;

	private TreeItemDTO dItemDTO;


	@BeforeAll
	public static void setup() {
		treeItemDTOConversion = new TreeDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		treeItemDTOConversion = null;
	}


	@BeforeEach
	void setUp() throws Exception {

		//Create an empty root directory item DTO
		dItemDTO = new TreeItemDTO("1","dir","/home/dir",Directory.DIRECTORY_TYPE);
		//Create a file item DTO
		fItemDTO = new TreeItemDTO("1","filename","/home/filename.pdf",  MyFile.FILE_TYPE);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.fItemDTO=null;
		this.dItemDTO=null;

	}
	
	@Test
	public void test_GivenFileItemDTO_WhenConvertDTOToEntity_ThenReturnFileItem()  {

		//WhenConvertDTOToEntity
		FileTreeItem actual = treeItemDTOConversion.convertFileItemDTOtoFileItem(fItemDTO);

		//ThenReturnFileItem
		//item_id
		assertNotNull(actual.getItem_id());
		assertEquals(fItemDTO.getItem_id(), String.valueOf(actual.getItem_id()));
		//item name
		assertNotNull(actual.getName());
		assertEquals(fItemDTO.getName(), actual.getName());
		//item_local_path	
		assertNotNull(actual.getItem_local_path());
		assertEquals(fItemDTO.getItem_local_path(), actual.getItem_local_path());
		//item type
		assertNotNull(((MyFile) actual).getItem_type());
		assertEquals(fItemDTO.getItem_type(), ((MyFile) actual).getItem_type());
	}

	@Test
	public void test_GivenEmptyDirectoryItemDTO_WhenConvertDTOtoEntity_ThenReturnDirEmptyDirectoryItem()  {

		//WhenConvertDTOToEntity
		FileTreeItem actualDItem = treeItemDTOConversion.convertDirectoryItemDTOtoDirectoryItem(dItemDTO);

		//Then Return Directory Item
		//item_id
		assertNotNull(actualDItem.getItem_id());
		assertEquals(dItemDTO.getItem_id(), String.valueOf(actualDItem.getItem_id()));
		//name
		assertNotNull(actualDItem.getName());
		assertEquals(dItemDTO.getName(), actualDItem.getName());
		//item_local_path
		assertNotNull(actualDItem.getItem_local_path());
		assertEquals(dItemDTO.getItem_local_path(), actualDItem.getItem_local_path());
		//children
		assertNotNull(((Directory) actualDItem).getChildren());
		assertEquals(dItemDTO.getChildren().size(), ((Directory) actualDItem).getChildren().size());
		//item_type
		assertNotNull(((Directory) actualDItem).getItem_type());
		assertEquals(dItemDTO.getItem_type(), ((Directory) actualDItem).getItem_type());
	}

	@Test
	public void test_GivenDirectoryDTOContainsOneDirectoryDTO_WhenConvertDTOToEntity_ThenReturnDirectoryContainsOneDirectory() {
		//Given a directory item DTO
		TreeItemDTO dItemDTO1 = new TreeItemDTO("1","dir1","/home/dir1/",  "folder");
		//Given a directory item DTO
		TreeItemDTO dItemDTO2 = new TreeItemDTO("2","dir2","/home/dir1/dir2/","folder");

		//add a child to parent directory's children list
		List<TreeItemDTO> children = new ArrayList<>();
		children.add(dItemDTO2);
		dItemDTO1.setChildren(children);

		//When convert directory item DTO to directory item
		FileTreeItem dItem = treeItemDTOConversion.convertDirectoryItemDTOtoDirectoryItem(dItemDTO1);

		//Then return parent and child item DTO
		assertNotNull(dItem.getItem_id());
		assertEquals(Long.valueOf(dItemDTO1.getItem_id()), dItem.getItem_id());
		assertNotNull(dItem.getName());
		assertEquals(dItemDTO1.getName(), dItem.getName());
		assertEquals(dItemDTO1.getItem_local_path(), dItem.getItem_local_path());
		assertEquals(Directory.class.toString(), dItem.getClass().toString());

		assertNotNull(dItemDTO1.getChildren().get(0).getName());
		assertEquals(Long.valueOf(dItemDTO1.getChildren().get(0).getItem_id()),((Directory) dItem).getChildren().get(0).getItem_id());
		assertNotNull(dItemDTO1.getChildren().get(0).getName());
		assertEquals(dItemDTO1.getChildren().get(0).getName(),((Directory) dItem).getChildren().get(0).getName());
		assertEquals(Directory.class.toString(),((Directory) dItem).getChildren().get(0).getClass().toString());
		assertNotNull(dItemDTO1.getChildren().get(0).getChildren());
		assertEquals(dItemDTO1.getChildren().get(0).getChildren().size(), ((Directory) ((Directory) dItem).getChildren().get(0)).getChildren().size());
	}

	@Test
	public void test_GivenDirectoryDTOContainsOneDirectoryDTOAndOneFileDTO_WhenConvertDTOToEntity_ThenReturnDirectoryContainsOneDirectoryAndOneFile()  {
		//Given two items DTO
		TreeItemDTO parentItemDTO = new TreeItemDTO("1","dir1","/home/dir1/","folder");
		TreeItemDTO childDItemDTO = new TreeItemDTO("2","dir2","/home/dir1/dir2/",  "folder");
		//Given a file item DTO
		TreeItemDTO fItemDTO = new TreeItemDTO("3","filename","/home/dir1/filename.pdf",  "file");
		//add a child to parent directory's children list
		List<TreeItemDTO> children = new ArrayList<>();
		children.add(childDItemDTO);
		children.add(fItemDTO);
		parentItemDTO.setChildren(children);

		//When convert directory item DTO to directory item
		FileTreeItem parentItem = treeItemDTOConversion.convertDirectoryItemDTOtoDirectoryItem(parentItemDTO);

		//parent
		assertNotNull(parentItem.getItem_id());
		assertNotNull(parentItem.getName());
		assertEquals(parentItem.getItem_id(), Long.valueOf(parentItemDTO.getItem_id()));
		assertNotNull(parentItem.getName());
		assertEquals(parentItem.getName(), parentItemDTO.getName());
		assertEquals(parentItem.getItem_local_path(), parentItemDTO.getItem_local_path());
		assertEquals(parentItem.getClass().toString(), Directory.class.toString());

		//directory child 1
		assertEquals(parentItemDTO.getChildren().get(0).getItem_id(),"2");
		assertEquals(((Directory) parentItem).getChildren().get(0).getItem_id(),2L);
		assertNotNull(((Directory) parentItem).getChildren().get(0).getName());
		assertEquals(((Directory) parentItem).getChildren().get(0).getName(),parentItemDTO.getChildren().get(0).getName());
		assertEquals(((Directory) parentItem).getChildren().get(0).getClass().toString(), Directory.class.toString());
		assertNotNull(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren());
		assertEquals(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().size(), parentItemDTO.getChildren().get(0).getChildren().size());

		//file child 2
		assertNotNull(((Directory) parentItem).getChildren().get(1).getItem_id());
		assertEquals(((Directory) parentItem).getChildren().get(1).getItem_id(),Long.valueOf(parentItemDTO.getChildren().get(1).getItem_id()));
		assertNotNull(((Directory) parentItem).getChildren().get(1).getName());
		assertEquals(((Directory) parentItem).getChildren().get(1).getName(),parentItemDTO.getChildren().get(1).getName());
		assertEquals(((Directory) parentItem).getChildren().get(1).getClass().toString(), MyFile.class.toString());
	}

	//fileTreeItemDTO  => fileTreeItem
	//create 
	
	
	
	@Test 
	public void test_GivenDirectoryDTOContainsOneFileItemDTO_WhenConvertEntityToDTO_ThenReturnDirectoryContainsOneFileItem() {
		//Given a directory item DTO
		TreeItemDTO dItemDTO = new TreeItemDTO("1","dir","/home/dir/","folder");
		//Given a file item DTO
		TreeItemDTO fItemDTO = new TreeItemDTO("2","filename","/home/dir/filename.pdf",  "file");
	
		//add a child to parent directory's children list
		List<TreeItemDTO> children = new ArrayList<>();
		children.add(fItemDTO);
		dItemDTO.setChildren(children);
	
		//When convert directory item DTO to directory item
		FileTreeItem dItem = treeItemDTOConversion.convertDirectoryItemDTOtoDirectoryItem(dItemDTO);
	
		//Then return directory parent and child file item
		assertNotNull(dItem.getItem_id());
		assertEquals(Long.valueOf(dItemDTO.getItem_id()), dItem.getItem_id());
		assertNotNull(dItem.getName());
		assertEquals(dItemDTO.getName(), dItem.getName());
		assertEquals(dItemDTO.getItem_local_path(), dItem.getItem_local_path());
		assertEquals(Directory.class.toString(), dItem.getClass().toString());
	
		assertEquals(dItemDTO.getChildren().size(), ((Directory) dItem).getChildren().size());
		assertNotNull(dItemDTO.getChildren().get(0).getItem_id());
		assertEquals(Long.valueOf(dItemDTO.getChildren().get(0).getItem_id()), ((Directory) dItem).getChildren().get(0).getItem_id());
		assertNotNull(dItemDTO.getChildren().get(0).getName());
		assertEquals(dItemDTO.getChildren().get(0).getName(), ((Directory) dItem).getChildren().get(0).getName());
		assertEquals(MyFile.class.toString(), ((Directory) dItem).getChildren().get(0).getClass().toString());
	}

	@Test
	public void test_GivenDirectoryDTOContainsOneDirectoryDTOContainsOneFileDTO_WhenConvertEntityToDTO_ThenReturnDirectoryContainsOneDirectoryContainsOneFile() {
		//Given two items DTO
		TreeItemDTO parentItemDTOId1 = new TreeItemDTO("1","dir1","/home/dir1/","folder");
		TreeItemDTO childDItemDTOId2 = new TreeItemDTO("2","dir2","/home/dir1/dir2/","folder");
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
	
		//When convert directory item DTO to directory item
		FileTreeItem parentItem = treeItemDTOConversion.convertDirectoryItemDTOtoDirectoryItem(parentItemDTOId1);
	
		assertNotNull(parentItem.getItem_id());
		assertNotNull(parentItem.getName());
		assertEquals(parentItem.getItem_id(), Long.valueOf(parentItemDTOId1.getItem_id()));
		assertNotNull(parentItem.getName());
		assertEquals(parentItem.getName(), parentItemDTOId1.getName());
		assertEquals(parentItem.getItem_local_path(), parentItemDTOId1.getItem_local_path());
		assertEquals(parentItem.getClass().toString(), Directory.class.toString());
	
		assertEquals(((Directory) parentItem).getChildren().get(0).getItem_id(),Long.valueOf(parentItemDTOId1.getChildren().get(0).getItem_id()));
		assertEquals(((Directory) parentItem).getChildren().get(0).getItem_id(),2L);
		assertNotNull(((Directory) parentItem).getChildren().get(0).getName());
		assertEquals(((Directory) parentItem).getChildren().get(0).getName(),parentItemDTOId1.getChildren().get(0).getName());
		assertEquals(Directory.class.toString(), ((Directory) parentItem).getChildren().get(0).getClass().toString());
		assertNotNull(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren());
		assertEquals(parentItemDTOId1.getChildren().get(0).getChildren().size(), ((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().size());
		assertNotNull(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().get(0));
		assertNotNull(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().get(0).getItem_id());
		assertEquals(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().get(0).getItem_id(),Long.valueOf(parentItemDTOId1.getChildren().get(0).getChildren().get(0).getItem_id()));
		assertNotNull(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().get(0).getName());
		assertEquals(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().get(0).getName(), parentItemDTOId1.getChildren().get(0).getChildren().get(0).getName());
		assertEquals(((Directory) ((Directory) parentItem).getChildren().get(0)).getChildren().get(0).getClass().toString(), MyFile.class.toString());
	}
}
