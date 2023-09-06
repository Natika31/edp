package com.projet.edp.directoryViewerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.projet.edp.fileTree.dto.TreeDTOConversion;
import com.projet.edp.fileTree.dto.TreeItemDTO;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;

class TreeDTOConversionItemEntityToDTOTest {

	private static TreeDTOConversion treeItemDTOConversion ;

	private Directory dItem;

	private FileTreeItem fItem;

	private FileContent fileContent;

	private FileTreeItem childDItem;


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
		//Create a root directory
		dItem = new Directory("root","/root");
		dItem.setItem_id(1L);

		//Create a file item and its content
		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		fItem = new MyFile("filename","/home/dir/filename.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent ); 
		fItem.setItem_id(1L);

		//Create a directory item
		childDItem = new Directory("dir","/root/dir/");
		childDItem.setItem_id(2L);


	}

	@AfterEach
	void tearDown() throws Exception {
		dItem = null;
		fItem = null;
		fileContent = null;
		childDItem = null;

	}

	//file and its content
	@Test
	public void test_GivenFileItem_WhenConvertEntityToDTO_ThenReturnFileItemDTO() throws FileNotFoundException, IOException  {

		//When convert file item to file item DTO
		TreeItemDTO actualfItemDTO = treeItemDTOConversion.convertEntityToDTO(fItem);

		//Then return item DTO
		//item_id
		assertNotNull(actualfItemDTO.getItem_id());
		assertEquals(fItem.getItem_id(), Long.valueOf(actualfItemDTO.getItem_id()));
		//name
		assertNotNull(actualfItemDTO.getName());
		assertEquals(fItem.getName(), actualfItemDTO.getName());
		//item_local_path
		assertNotNull(actualfItemDTO.getItem_local_path());
		assertEquals(fItem.getItem_local_path(), actualfItemDTO.getItem_local_path());
		//item_type
		assertNotNull(actualfItemDTO.getItem_type());
		assertEquals(((MyFile) fItem).getItem_type(), actualfItemDTO.getItem_type());
		//children
		assertFalse(actualfItemDTO.getChildren().size() > 0);
	}
	
	//empty root directory
	@Test
	public void test_GivenEmptyDirectoryItem_WhenConvertEntityToDTO_ThenReturnDirItemDTOWithZeroChildren() throws FileNotFoundException, IOException  {

		//When convert directory item to directory item  DTO
		TreeItemDTO actualDirItemDTO = treeItemDTOConversion.convertEntityToDTO(dItem);		

		//Then return directory DTO
		//item_id
		assertNotNull(actualDirItemDTO.getItem_id());
		assertEquals(dItem.getItem_id(), Long.valueOf(actualDirItemDTO.getItem_id()));
		//name
		assertNotNull(actualDirItemDTO.getName());
		assertEquals(dItem.getName(),actualDirItemDTO.getName());
		//item_local_path
		assertNotNull(actualDirItemDTO.getItem_local_path());
		assertEquals(dItem.getItem_local_path(), actualDirItemDTO.getItem_local_path());
		//item_type
		assertNotNull(actualDirItemDTO.getItem_type());
		assertEquals(Directory.DIRECTORY_TYPE, actualDirItemDTO.getItem_type());
		//children
		assertNotNull(actualDirItemDTO.getChildren());
		assertEquals(((Directory) dItem).getChildren().size(), actualDirItemDTO.getChildren().size());
	}

	//one child
	@Test
	public void test_GivenDirectoryContainsOneFileItem_WhenConvertEntityToDTO_ThenReturnDirectoryDTOContainsOneFileDTO() throws FileNotFoundException, IOException {
		//Given an empty root directory and a file item 
		//When add a child to parent directory's children list and convert directory to directory item DTO
		((Directory) dItem).addChildren(fItem);
		TreeItemDTO actualItemDTO = treeItemDTOConversion.convertEntityToDTO(dItem);

		//Then return parent directory DTO
		//item_id
		assertNotNull(actualItemDTO.getItem_id());
		assertEquals(dItem.getItem_id(), Long.valueOf(actualItemDTO.getItem_id()));
		//name
		assertNotNull(actualItemDTO.getName());
		assertEquals(dItem.getName(), actualItemDTO.getName());
		//item_local_path
		assertNotNull(actualItemDTO.getItem_local_path());
		assertEquals(dItem.getItem_local_path(), actualItemDTO.getItem_local_path());
		//item_type
		assertNotNull(actualItemDTO.getItem_type());
		assertEquals(dItem.getItem_type(), actualItemDTO.getItem_type());
		//children
		assertNotNull(actualItemDTO.getChildren());
		assertEquals(((Directory) dItem).getChildren().size(), actualItemDTO.getChildren().size());

		//Then return child file item DTO
		//item_id
		assertNotNull(actualItemDTO.getChildren().get(0).getItem_id());
		assertEquals(((Directory) dItem).getChildren().get(0).getItem_id(), Long.valueOf(actualItemDTO.getChildren().get(0).getItem_id()));
		//name
		assertNotNull(actualItemDTO.getChildren().get(0).getName());
		assertEquals(((Directory) dItem).getChildren().get(0).getName(),actualItemDTO.getChildren().get(0).getName());
		//item_local_path
		assertNotNull(actualItemDTO.getItem_local_path());
		assertEquals((((Directory) dItem).getChildren()).get(0).getItem_local_path(), actualItemDTO.getChildren().get(0).getItem_local_path());
		//item_type
		assertNotNull(actualItemDTO.getItem_type());
		assertEquals(((MyFile) ((Directory) dItem).getChildren().get(0)).getItem_type(), actualItemDTO.getChildren().get(0).getItem_type());
		//children
		assertEquals(0, actualItemDTO.getChildren().get(0).getChildren().size());	
	}

	@Test
	public void test_GivenDirectoryContainsOneDirectoryItem_WhenConvertEntityToDTO_ThenReturnDirectoryDTOContainsOneDirectoryDTO() throws FileNotFoundException, IOException {
		//add a child to parent directory's children list
		dItem.addChildren(childDItem);

		//When convert directory to directory item DTO
		TreeItemDTO actualDirItemDTO = treeItemDTOConversion.convertEntityToDTO(dItem);

		//Then return parent DTO
		//item_id
		assertNotNull(actualDirItemDTO.getItem_id());
		assertEquals(dItem.getItem_id(), Long.valueOf(actualDirItemDTO.getItem_id()));
		//name
		assertNotNull(actualDirItemDTO.getName());
		assertEquals(dItem.getName(),actualDirItemDTO.getName());
		//item_local_path
		assertNotNull(actualDirItemDTO.getItem_local_path());
		assertEquals(dItem.getItem_local_path(), actualDirItemDTO.getItem_local_path());
		//item_type
		assertNotNull(actualDirItemDTO.getItem_type());
		assertEquals(Directory.DIRECTORY_TYPE, actualDirItemDTO.getItem_type());
		//children
		assertNotNull(actualDirItemDTO.getChildren());
		assertEquals(((Directory) dItem).getChildren().size(), actualDirItemDTO.getChildren().size());
		//and child item DTO
		//item_id
		assertNotNull(actualDirItemDTO.getChildren().get(0).getItem_id());
		assertEquals(((Directory) dItem).getChildren().get(0).getItem_id(), Long.valueOf(actualDirItemDTO.getChildren().get(0).getItem_id()));
		//name
		assertNotNull(actualDirItemDTO.getChildren().get(0).getName());
		assertEquals(((Directory) dItem).getChildren().get(0).getName(),actualDirItemDTO.getChildren().get(0).getName());
		//item_local_path
		assertNotNull(actualDirItemDTO.getChildren().get(0).getItem_local_path());
		assertEquals(((Directory) dItem).getChildren().get(0).getItem_local_path(), actualDirItemDTO.getChildren().get(0).getItem_local_path());
		//item_type
		assertNotNull(actualDirItemDTO.getChildren().get(0).getItem_type());
		assertEquals(Directory.DIRECTORY_TYPE, actualDirItemDTO.getChildren().get(0).getItem_type());
		//children
		assertNotNull(actualDirItemDTO.getChildren().get(0).getChildren());
		assertEquals(((Directory) ((Directory) dItem).getChildren().get(0)).getChildren().size(), actualDirItemDTO.getChildren().get(0).getChildren().size());
	}

	//one child contains one child
	@Test
	public void test_GivenDirectoryContainsOneDirectoryItemContainsOneFileItem_WhenConvertEntityToDTO_ThenReturnDirectoryDTOContainsOneDirectoryDTOContainsOneFileDTO() throws FileNotFoundException, IOException {

		//add a child to child directory's children list
		((Directory) childDItem).addChildren(fItem);
		//add a child to parent directory's children list
		dItem.addChildren(childDItem);

		//When convert directory to directory item DTO
		TreeItemDTO actualDirItemDTO = treeItemDTOConversion.convertEntityToDTO(dItem);
		
		//THEN
		//root directory
		//item_id
		assertNotNull(actualDirItemDTO.getItem_id());
		assertEquals(dItem.getItem_id(), Long.valueOf(actualDirItemDTO.getItem_id()));
		//name
		assertNotNull(actualDirItemDTO.getName());
		assertEquals(dItem.getName(),actualDirItemDTO.getName());
		//item_local_path
		assertNotNull(actualDirItemDTO.getItem_local_path());
		assertEquals(dItem.getItem_local_path(), actualDirItemDTO.getItem_local_path());
		//item_type
		assertNotNull(actualDirItemDTO.getItem_type());
		assertEquals(Directory.DIRECTORY_TYPE, actualDirItemDTO.getItem_type());
		
		//child directory DTO
		//item_id
		assertNotNull(actualDirItemDTO.getChildren().get(0).getItem_id());
		assertEquals(dItem.getChildren().get(0).getItem_id(), Long.valueOf(actualDirItemDTO.getChildren().get(0).getItem_id()));
		//name
		assertNotNull(actualDirItemDTO.getChildren().get(0).getName());
		assertEquals(dItem.getChildren().get(0).getName(),actualDirItemDTO.getChildren().get(0).getName());
		//item_local_path
		assertNotNull(actualDirItemDTO.getChildren().get(0).getItem_local_path());
		assertEquals(dItem.getChildren().get(0).getItem_local_path(), actualDirItemDTO.getChildren().get(0).getItem_local_path());
		//item_type
		assertNotNull(actualDirItemDTO.getChildren().get(0).getItem_type());
		assertEquals(((Directory) dItem.getChildren().get(0)).getItem_type(), actualDirItemDTO.getChildren().get(0).getItem_type());
		//child file DTO
		//item_id
		assertNotNull(actualDirItemDTO.getChildren().get(0).getChildren().get(0).getItem_id());
		assertEquals(((Directory) dItem.getChildren().get(0)).getChildren().get(0).getItem_id(), Long.valueOf(actualDirItemDTO.getChildren().get(0).getChildren().get(0).getItem_id()));
		//name
		assertNotNull(actualDirItemDTO.getChildren().get(0).getChildren().get(0).getName());
		assertEquals(((Directory) dItem.getChildren().get(0)).getChildren().get(0).getName(), actualDirItemDTO.getChildren().get(0).getChildren().get(0).getName());
		//item_local_path
		assertNotNull(actualDirItemDTO.getChildren().get(0).getChildren().get(0).getItem_local_path());
		assertEquals(((Directory) dItem.getChildren().get(0)).getChildren().get(0).getItem_local_path(), actualDirItemDTO.getChildren().get(0).getChildren().get(0).getItem_local_path());
		//item_type
		assertNotNull(actualDirItemDTO.getChildren().get(0).getChildren().get(0).getItem_type());
		assertEquals(((MyFile) ((Directory) dItem.getChildren().get(0)).getChildren().get(0)).getItem_type(), actualDirItemDTO.getChildren().get(0).getChildren().get(0).getItem_type());
		//children
		assertFalse(actualDirItemDTO.getChildren().get(0).getChildren().get(0).getChildren().size() > 0);	
		
	}

	//two different type children
	@Test
	public void test_GivenDirectoryContainsOneDirectoryAndOneFile_WhenConvertEntityToDTO_ThenReturnDirectoryDTOContainsOneDirectoryDTOAndOneFileDTO() throws FileNotFoundException, IOException {
		//Given two directory items
		FileTreeItem dItem1 = new Directory("dir1","/home/dir1/" );
		dItem1.setItem_id(1L);
		FileTreeItem dItem2 = new Directory("dir2","/home/dir1/dir2/");
		dItem2.setItem_id(2L);
		//Given a file item
		FileContent fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		FileTreeItem fItem = new MyFile("filename","/home/dir/filename.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent ); 
		fItem.setItem_id(3L);
		//add a child to parent directory's children list
		Directory parentDir = (Directory) dItem1;
		parentDir.addChildren(dItem2);
		parentDir.addChildren(fItem);

		//When convert directory to directory item DTO
		TreeItemDTO itemDTO = treeItemDTOConversion.convertEntityToDTO(parentDir);

		//parent
		assertNotNull(parentDir.getItem_id());
		assertNotNull(parentDir.getName());
		assertEquals(parentDir.getItem_id(), Long.valueOf(itemDTO.getItem_id()));
		assertNotNull(parentDir.getName());
		assertEquals(parentDir.getName(), itemDTO.getName());
		assertEquals(parentDir.getItem_local_path(), itemDTO.getItem_local_path());
		assertEquals("folder", itemDTO.getItem_type());

		//directory child 1
		assertNotNull(itemDTO.getChildren().get(0).getItem_id());
		assertEquals(parentDir.getChildren().get(0).getItem_id(),Long.valueOf(itemDTO.getChildren().get(0).getItem_id()));
		assertNotNull(parentDir.getChildren().get(0).getName());
		assertEquals(parentDir.getChildren().get(0).getName(),itemDTO.getChildren().get(0).getName());
		assertEquals("folder", itemDTO.getChildren().get(0).getItem_type());
		assertNotNull(((Directory) parentDir.getChildren().get(0)).getChildren());
		assertEquals(((Directory) parentDir.getChildren().get(0)).getChildren().size(), itemDTO.getChildren().get(0).getChildren().size());

		//file child 2
		assertNotNull(itemDTO.getChildren().get(1).getItem_id());
		assertEquals(parentDir.getChildren().get(1).getItem_id(),Long.valueOf(itemDTO.getChildren().get(1).getItem_id()));
		assertNotNull(parentDir.getChildren().get(1).getName());
		assertEquals(parentDir.getChildren().get(1).getName(),itemDTO.getChildren().get(1).getName());
		assertEquals("file", itemDTO.getChildren().get(1).getItem_type());

	}


}
