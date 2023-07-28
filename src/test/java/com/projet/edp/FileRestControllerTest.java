package com.projet.edp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.service.FileViewerService;
import com.projet.edp.fileViewer.ui.FileViewerController;

@WebMvcTest(FileViewerController.class)
class FileRestControllerTest {
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @MockBean
	  private FileViewerService service;

	@Test
	void GivenSelectedFile_whenRequestGETFileIdEquals1_thenGetStoredFileIdEquals1() throws Exception {
		

		Optional<MyFile> selectedItem = saveFile("/home/","Dans mon île", "pdf", "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		when(service.findFileById(1L)).thenReturn(selectedItem);
		this.mockMvc.perform(get("/api/file?file_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("\"file_id\":1,\"file_destination_path\":\"/home/\",\"file_name\":\"Dans mon Ã®le\",\"file_format\":\"pdf\",\"file_origin_location\":\"C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf\",\"file_content\":{\"file_content_id\":1,\"binary_content\":")));

	}

	private Optional<MyFile> saveFile(String file_path, String file_name, String file_format, String file_content_location) throws FileNotFoundException, IOException {
		MyFile selectedItem = new MyFile(file_path, file_name, file_format, file_content_location);
		selectedItem.setFile_id(1L);
		selectedItem.getFile_content().setFile_content_id(1L);
		return Optional.of(selectedItem);
	}
}
