package com.projet.edp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.projet.edp.directoryViewer.domain.Directory;
import com.projet.edp.directoryViewer.service.DirectoryService;
import com.projet.edp.directoryViewer.ui.DirectoryController;
import com.projet.edp.fileViewer.domain.MyFile;

@WebMvcTest(DirectoryController.class)
class DirectoryRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DirectoryService directoryService;

	@Test
	void GivenSelectedDirectory_whenRequestGETEmptyDirectoryIdEquals1_thenGetStoredDirectoryIdEquals1() throws Exception {
		Directory emptyDirectory = new Directory("/home/", "Henri Salvador");
		emptyDirectory.setDirectory_id(1L);
		when(directoryService.findDirectoryById(1L)).thenReturn(Optional.of(emptyDirectory));
		this.mockMvc.perform(get("/api/directory?directory_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("directory_id\":1,\"directory_local_path\":\"/home/\",\"directory_name\":\"Henri Salvador\",\"children\":[]")))
		;
	}

	@Test
	void GivenSelectedDirectory_whenRequestGETNonEmptyDirectoryIdEquals2_thenGetStoredDirectoryIdEquals2AndGetItsContent() throws Exception {

		Directory nonEmptyDirectory = new Directory("/home/", "Henri Salvador");
		nonEmptyDirectory.setDirectory_id(2L);
		MyFile file = new MyFile("/home/","Dans mon île", "pdf", "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", null);
		file.setFile_id(1L);
		nonEmptyDirectory.addChildren(file);
		when(directoryService.findDirectoryById(2L)).thenReturn(Optional.of(nonEmptyDirectory));
		this.mockMvc.perform(get("/api/directory?directory_id=2")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("directory_id\":2,\"directory_local_path\":\"/home/\",\"directory_name\":\"Henri Salvador\",\"children\":[{\"file_id\":\"1\",\"file_name\":\"Dans mon ")))
		;
	}
}
