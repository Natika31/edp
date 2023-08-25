package com.projet.edp.DTO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.domain.FileContent;
import com.projet.edp.fileTree.domain.MyFile;
import com.projet.edp.fileTree.dto.DirectoryDTO;
import com.projet.edp.fileTree.dto.DirectoryDTOConversion;

class DirectoryDTOConversionTest {

	private static DirectoryDTOConversion directoryDTOConversion ;

	@BeforeAll
	static void setup() {
		directoryDTOConversion = new DirectoryDTOConversion();
	}

	@Test
	void test_GivenEmptyDirectory_WhenConvertEntityToDTO_ThenReturnEmptyDirectoryDTO() {
		Directory directory = new Directory();
		directory.setItem_id(1L);
		directory.setItem_local_path("/home/");
		directory.setName("home");

		DirectoryDTO directoryDTO = directoryDTOConversion.convertEntityToDTO(directory);
		
		assertEquals(directory.getName(), directoryDTO.getName());
		assertEquals(directory.getItem_local_path(), directoryDTO.getItem_local_path());
		assertEquals(directory.getChildren().size(), directoryDTO.getChildren().size());
	}

	@Test
	void test_GivenDirectoryContainsOneFile_WhenConvertEntityToDTO_ThenReturnDirectoryDTOContainsOneFileDTO() throws FileNotFoundException, IOException {
		Directory directory = new Directory("home", "/home");
		directory.setItem_id(1L);
		MyFile file = new MyFile("/home/file.pdf", "file", "pdf", "/myOriginPath/", new FileContent(new byte[] {37, 80, 68, 70, 45, 49, 46, 55, 10, 10, 52, 32, 48, 32, 111, 98, 106, 10, 40, 73, 100, 101, 110, 116, 105, 116, 121, 41, 10, 101, 110, 100, 111, 98, 106, 10, 53, 32, 48, 32, 111, 98, 106, 10, 40, 65, 100, 111, 98, 101, 41, 10, 101, 110, 100, 111, 98, 106, 10, 56, 32, 48, 32, 111, 98, 106, 10, 60, 60, 10, 47, 70, 105, 108, 116, 101, 114, 32, 47, 70, 108, 97, 116, 101, 68, 101, 99, 111, 100, 101, 10, 47, 76, 101, 110, 103, 116, 104, 32, 49, 56, 50, 50, 55, 51, 10, 47, 76, 101, 110, 103, 116, 104, 49, 32, 53, 51, 56, 57, 54, 48, 10, 47, 84, 121, 112, 101, 32, 47, 83, 116, 114, 101, 97, 109, 10, 62, 62, 10, 115, 116, 114, 101, 97, 109, 10, 120, -100, -20, -67, 9, 96, -108, -59, -7, 63, -2, -68, -17, -69, -9, -107, 77, 54, -39, 108, -18, 55, -39, 100, 115, 111, 78, 66, 18, 34, -28, 38, -127, 16, 66, 14, 72, 16, 33, 75, -78, 33, -111, 92, -18, 110, -72, 4, -119, 55, 69, -68, -15, -62, -117, 90, 107, 61, 80, 67, 68, -115, 74, 53, 42, 69, -85, 120, 84, -83, 82, 69, -117, 71, -67, 10, -118, 86, -83, 69, 72, -2, -49, -52, -68, -101, 11, -76, -108, -6, -3, -1, -38, -17, 119, 103, -104, -49, 103, 102, -34, -103, 103, -98, 121, -26, 124, 119, 19, 2, 28, 0, -104, 17, 100, -48, 92, 90, 87, 57, -69, -10, -35, -99, -117, 65, 21, 118, 11, 64, -60, -82, -78, -30, -46, -6, -115, -78, -67, 15, 2, 23, -7, 13, 22, -64, 116, 85, -55, -27, -111, 29, 45, -64, -123, -121, 3, -16, -5, 103, -105, -106, -107, -17, 121, -31, -83, -125, 32, 91, -72, 15, 64, -15, -63, -20, -102, -7, 117, -107, -33, -66, -11, 21, -56, -50, 26, 0, -31, -73, -97, -49, -82, 107, 40, -66, 71, 119, -28, 50, -32, 98, 83, 64, -56, -68, 109, 126, 93, 90, -26, -19, 95, -74, 124, 5, -64, 125, -118, -83, 54, -73, 116, 57, 122, 71, -123, -122, -121, 0, 86, -94, 124, -95, -65, 101, -107, 71, -100, -11, 120, -29, 33, -128, 43, 59, 81, -34, -90, -74, -34, 21, 93, -65, -4, -68, 120, 7, 64, -113, 7, 64, 109, 90, -31, 112, -9, -126, 5, -84, -40, -66, 14, -21, 27, 87, 116, -82, 109, -69, 60, -73, 63, 5, -32, 58, -84, 31, -71, -89, -35, -23, 104, -3, 126, -55, -38, 11, 80, 62, -42, -121, -100, 118, -52, -48, 63, 17, -2, 58, -90, -17, -60, 116, 108, 123, -105, 103, -51, 29, -77, -116, -97, -93, -18, 21, 0, 29, 59, 86, 58, 93, -35, -4, 83, 66, 6, -64, 95, -115, -8, -68, -70, -77, -89, -59, -15, 75, -5, 77, 103, 3, -20, 26, -62, -22, 11, -69, 28, 107, 122, 19, -18, 10, -2, 61, -42, 127, 30, -97, -117, 93, 78, -113, 99, -37, 5, -37, 87, 1, 103, -67, 27, -45, 23, 117}));
		directory.addChildren(file);
		
		DirectoryDTO directoryDTO = directoryDTOConversion.convertEntityToDTO(directory);
		
		assertEquals(directoryDTO.getChildren().size(),directory.getChildren().size() );
		assertEquals(directory.getChildren().get(0).getName(), directoryDTO.getChildren().get(0).getName());
	}

	@Test
	void test_GivenEmptyDirectoryDTO_WhenConvertDTOToEntity_ThenReturnEmptyDirectory() {
		DirectoryDTO directoryDTO = new DirectoryDTO("1", "home", "/home");
		
		Directory directory = directoryDTOConversion.convertDTOtoEntities(directoryDTO);
		
		assertEquals(directory.getName(), directoryDTO.getName());
		assertEquals(directory.getItem_local_path(), directoryDTO.getItem_local_path());
		assertEquals(directory.getChildren().size(), directoryDTO.getChildren().size());	}
}
