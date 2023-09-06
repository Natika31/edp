package com.projet.edp.directoryViewerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.dto.DirectoryDTO;
import com.projet.edp.fileTree.dto.DirectoryDTOConversion;
import com.projet.edp.fileTree.dto.TreeItemDTO;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;

import jakarta.persistence.Column;

class DirectoryDTOConversionDTOToEntityTest {

	private static DirectoryDTOConversion directoryDTOConversion ;

	@BeforeAll
	public static void setup() {
		directoryDTOConversion = new DirectoryDTOConversion();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		directoryDTOConversion = null;
	}

	private DirectoryDTO rootDirectoryDTO;
	private TreeItemDTO childFileDTO;
	private TreeItemDTO childDirectoryDTO;

	@BeforeEach
	void setUp() throws Exception {

		//create a root directory dto
		rootDirectoryDTO = new DirectoryDTO("1","root", "/root");
		//create a child file DTO
		childFileDTO = new TreeItemDTO("1", "Dans mon ile", "/root/file.pdf", MyFile.FILE_TYPE);
		//create a child directory DTO
		childDirectoryDTO = new TreeItemDTO("2", "dir", "/root/dir", Directory.DIRECTORY_TYPE);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.rootDirectoryDTO = null;
		this.childFileDTO = null;
		this.childDirectoryDTO = null;
	}

	@Test
	public void test_GivenEmptyDirectoryDTO_WhenConvertDTOToEntity_ThenReturnEmptyDirectory() {
		//WHEN
		Directory actualDirectory = directoryDTOConversion.convertDTOtoEntities(rootDirectoryDTO);

		//THEN
		//item_id
		assertNotNull(actualDirectory.getItem_id());
		assertEquals(rootDirectoryDTO.getItem_id(), String.valueOf(actualDirectory.getItem_id()));
		//name
		assertNotNull(actualDirectory.getName());
		assertEquals(rootDirectoryDTO.getName(), actualDirectory.getName());
		//item_local_path
		assertNotNull(actualDirectory.getItem_local_path());
		assertEquals(rootDirectoryDTO.getItem_local_path(), actualDirectory.getItem_local_path());
		//children
		assertNotNull(actualDirectory.getChildren());
		assertEquals(rootDirectoryDTO.getChildren().size(), actualDirectory.getChildren().size());
		//item_type
		assertEquals(Directory.DIRECTORY_TYPE, actualDirectory.getItem_type());
	}

	/*
	 * @Test public void
	 * test_GivenDirectoryDTOContainsOneFileDTO_WhenConvertDTOToEntity_ThenReturnDirectoryContainsOneFile
	 * () { //WHEN rootDirectoryDTO.addChildrenDTO(childFileDTO); Directory
	 * actualDirectory =
	 * directoryDTOConversion.convertDTOtoEntities(rootDirectoryDTO);
	 * 
	 * //THEN //root directory //item_id
	 * assertNotNull(actualDirectory.getItem_id());
	 * assertEquals(rootDirectoryDTO.getItem_id(),
	 * String.valueOf(actualDirectory.getItem_id())); //name
	 * assertNotNull(actualDirectory.getName());
	 * assertEquals(rootDirectoryDTO.getName(), actualDirectory.getName());
	 * //item_local_path assertNotNull(actualDirectory.getItem_local_path());
	 * assertEquals(rootDirectoryDTO.getItem_local_path(),
	 * actualDirectory.getItem_local_path()); //children
	 * assertNotNull(actualDirectory.getChildren());
	 * assertEquals(rootDirectoryDTO.getChildren().size(),
	 * actualDirectory.getChildren().size()); //item_type
	 * assertEquals(Directory.DIRECTORY_TYPE, actualDirectory.getItem_type());
	 * 
	 * //root directory child file tree item
	 * 
	 * //0: file //item_id
	 * assertNotNull(actualDirectory.getChildren().get(0).getItem_id());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getItem_id(),
	 * String.valueOf(actualDirectory.getChildren().get(0).getItem_id()));
	 * //item_name assertNotNull(actualDirectory.getChildren().get(0).getName());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getName(),
	 * actualDirectory.getChildren().get(0).getName()); //item_local_path
	 * assertNotNull(actualDirectory.getChildren().get(0).getItem_local_path());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getItem_local_path(),
	 * actualDirectory.getChildren().get(0).getItem_local_path()); //item_type
	 * assertNotNull(((MyFile)
	 * actualDirectory.getChildren().get(0)).getItem_type());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getItem_type(), ((MyFile)
	 * actualDirectory.getChildren().get(0)).getItem_type()); }
	 */
	/*
	 * @Test public void
	 * test_GivenDirectoryDTOContainsOneDirectoryDTO_WhenConvertDTOToEntity_ThenReturnDirectoryContainsOneDirectory
	 * () { //WHEN rootDirectoryDTO.addChildrenDTO(childDirectoryDTO); Directory
	 * actualDirectory =
	 * directoryDTOConversion.convertDTOtoEntities(rootDirectoryDTO);
	 * 
	 * //THEN //root directory //item_id
	 * assertNotNull(actualDirectory.getItem_id());
	 * assertEquals(rootDirectoryDTO.getItem_id(),
	 * String.valueOf(actualDirectory.getItem_id())); //name
	 * assertNotNull(actualDirectory.getName());
	 * assertEquals(rootDirectoryDTO.getName(), actualDirectory.getName());
	 * //item_local_path assertNotNull(actualDirectory.getItem_local_path());
	 * assertEquals(rootDirectoryDTO.getItem_local_path(),
	 * actualDirectory.getItem_local_path()); //children
	 * assertNotNull(actualDirectory.getChildren());
	 * assertEquals(rootDirectoryDTO.getChildren().size(),
	 * actualDirectory.getChildren().size()); //item_type
	 * assertEquals(Directory.DIRECTORY_TYPE, actualDirectory.getItem_type());
	 * 
	 * //root directory child directory tree item //0: directory //item_id
	 * assertNotNull(actualDirectory.getChildren().get(0).getItem_id());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getItem_id(),
	 * String.valueOf(actualDirectory.getChildren().get(0).getItem_id()));
	 * //item_name assertNotNull(actualDirectory.getChildren().get(0).getName());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getName(),
	 * actualDirectory.getChildren().get(0).getName()); //item_local_path
	 * assertNotNull(actualDirectory.getChildren().get(0).getItem_local_path());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getItem_local_path(),
	 * actualDirectory.getChildren().get(0).getItem_local_path()); //item_type //
	 * assertNotNull((actualDirectory.getChildren().get(0)).getItem_type());
	 * assertEquals(rootDirectoryDTO.getChildren().get(0).getItem_type(),
	 * ((Directory) actualDirectory.getChildren().get(0)).getItem_type()); }
	 */
}
