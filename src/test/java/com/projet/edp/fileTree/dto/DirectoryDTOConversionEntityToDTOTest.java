package com.projet.edp.fileTree.dto;

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

class DirectoryDTOConversionEntityToDTOTest extends DirectoryDTOConversion {

	private static DirectoryDTOConversion directoryDTOConversion ;

	private Directory rootDirectory;
	
	private FileContent fileContent;
	
	private FileTreeItem file;
	
	private FileTreeItem childDirectory;

	@BeforeAll
	public static void setup() {
		directoryDTOConversion = new DirectoryDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		directoryDTOConversion = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		//create a root directory
		//entity
		rootDirectory = new Directory("root", "/root");
		rootDirectory.setItem_id(1L);
		//Create a file binary content 
		fileContent = new FileContent();
		byte[] binaryArray = fileContent.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		fileContent.setBinary_content(binaryArray);
		fileContent.setFile_content_id(1L);
		//Create a file
		file = new MyFile("Dans mon ile","/root/file.pdf","pdf","C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf",fileContent);
		file.setItem_id(1L);
		//create a child directory
		childDirectory = new Directory("dir", "/root/dir");
		childDirectory.setItem_id(2L);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.rootDirectory = null;
		this.fileContent = null;
		this.file = null;
	}

	@Test
	public void test_GivenEmptyDirectory_WhenConvertEntityToDTO_ThenReturnEmptyDirectoryDTO() {
		//WHEN
		DirectoryDTO actualDirectoryDTO = directoryDTOConversion.convertEntityToDTO(rootDirectory);

		//THEN
		//item_id
		assertNotNull(actualDirectoryDTO.getItem_id());
		assertEquals(rootDirectory.getItem_id(), Long.valueOf(actualDirectoryDTO.getItem_id()));
		//name
		assertNotNull(actualDirectoryDTO.getName());
		assertEquals(rootDirectory.getName(), actualDirectoryDTO.getName());
		//item_local_path
		assertNotNull(actualDirectoryDTO.getItem_local_path());
		assertEquals(rootDirectory.getItem_local_path(), actualDirectoryDTO.getItem_local_path());
		//children
		assertNotNull(actualDirectoryDTO.getChildren());
		assertEquals(rootDirectory.getChildren().size(), actualDirectoryDTO.getChildren().size());
		//item_type
		assertNotNull(actualDirectoryDTO.getItem_type());
		assertEquals(rootDirectory.getItem_type(), actualDirectoryDTO.getItem_type());
	}


	@Test
	public void test_GivenDirectoryContainsOneFile_WhenConvertEntityToDTO_ThenReturnDirectoryDTOContainsOneFileDTO() throws FileNotFoundException, IOException {
		//WHEN
		rootDirectory.addChildren(file);
		DirectoryDTO actualDirectoryDTO = directoryDTOConversion.convertEntityToDTO(rootDirectory);

		//THEN
		//root directory
		//item_id
		assertNotNull(actualDirectoryDTO.getItem_id());
		assertEquals(rootDirectory.getItem_id(), Long.valueOf(actualDirectoryDTO.getItem_id()));
		//name
		assertNotNull(actualDirectoryDTO.getName());
		assertEquals(rootDirectory.getName(), actualDirectoryDTO.getName());
		//item_local_path
		assertNotNull(actualDirectoryDTO.getItem_local_path());
		assertEquals(rootDirectory.getItem_local_path(), actualDirectoryDTO.getItem_local_path());
		//children
		assertNotNull(actualDirectoryDTO.getChildren());
		assertEquals(rootDirectory.getChildren().size(), actualDirectoryDTO.getChildren().size());
		//item_type
		assertNotNull(actualDirectoryDTO.getItem_type());
		assertEquals(rootDirectory.getItem_type(), actualDirectoryDTO.getItem_type());

		//root directory child file tree item

		//0: file
		//item_id
		assertNotNull(actualDirectoryDTO.getChildren().get(0).getItem_id());
		assertEquals(rootDirectory.getChildren().get(0).getItem_id(), Long.valueOf(actualDirectoryDTO.getChildren().get(0).getItem_id()));
		//item_name
		assertNotNull(actualDirectoryDTO.getChildren().get(0).getName());
		assertEquals(rootDirectory.getChildren().get(0).getName(), actualDirectoryDTO.getChildren().get(0).getName());
		//item_type
		assertNotNull(actualDirectoryDTO.getChildren().get(0).getItem_type());
		assertEquals(((MyFile) rootDirectory.getChildren().get(0)).getItem_type(), actualDirectoryDTO.getChildren().get(0).getItem_type());
	}

	@Test
	public void test_GivenDirectoryContainsOneDirectory_WhenConvertEntityToDTO_ThenReturnDirectoryDTOContainsOneDirectoryDTO() {
		//WHEN
		rootDirectory.addChildren(childDirectory);
		DirectoryDTO actualDirectoryDTO = directoryDTOConversion.convertEntityToDTO(rootDirectory);

		//THEN
		//root directory
		//item_id
		assertNotNull(actualDirectoryDTO.getItem_id());
		assertEquals(rootDirectory.getItem_id(), Long.valueOf(actualDirectoryDTO.getItem_id()));
		//name
		assertNotNull(actualDirectoryDTO.getName());
		assertEquals(rootDirectory.getName(), actualDirectoryDTO.getName());
		//item_local_path
		assertNotNull(actualDirectoryDTO.getItem_local_path());
		assertEquals(rootDirectory.getItem_local_path(), actualDirectoryDTO.getItem_local_path());
		//children
		assertNotNull(actualDirectoryDTO.getChildren());
		assertEquals(rootDirectory.getChildren().size(), actualDirectoryDTO.getChildren().size());
		//item_type
		assertNotNull(actualDirectoryDTO.getItem_type());
		assertEquals(rootDirectory.getItem_type(), actualDirectoryDTO.getItem_type());	

		//root directory child directory tree item
		//0: directory
		//item_id
		assertNotNull(actualDirectoryDTO.getChildren().get(0).getItem_id());
		assertEquals(rootDirectory.getChildren().get(0).getItem_id(), Long.valueOf(actualDirectoryDTO.getChildren().get(0).getItem_id()));
		//item_name
		assertNotNull(actualDirectoryDTO.getChildren().get(0).getName());
		assertEquals(rootDirectory.getChildren().get(0).getName(), actualDirectoryDTO.getChildren().get(0).getName());
		//item_local_path
		assertNotNull(actualDirectoryDTO.getChildren().get(0).getItem_local_path());
		assertEquals(rootDirectory.getChildren().get(0).getItem_local_path(), actualDirectoryDTO.getChildren().get(0).getItem_local_path());
		//item_type
		assertNotNull(actualDirectoryDTO.getChildren().get(0).getItem_type());
		assertEquals(((Directory) rootDirectory.getChildren().get(0)).getItem_type(), actualDirectoryDTO.getChildren().get(0).getItem_type());
	}


}
