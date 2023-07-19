package com.projet.edp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.service.FileServiceImpl;
import com.projet.edp.fileViewer.ui.FileController;

@WebMvcTest(FileController.class)
class FileRestControllerTest {
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @MockBean
	  private FileServiceImpl service;

	@Test
	void test() throws Exception {
		
		Optional<MyFile> selectedItem = createTestSelectedItem(1L,"/home/","Dans mon île", "pdf", "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		
		when(service.findFileById(1L)).thenReturn(selectedItem);
		this.mockMvc.perform(get("/api/file?file_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("\"file_id\":1,\"file_path\":\"/home/\",\"file_name\":")));

	}

	private Optional<MyFile> createTestSelectedItem(Long file_id, String file_path, String file_name, String file_format, String file_content_location) throws FileNotFoundException, IOException {
		MyFile selectedItem = new MyFile();
		selectedItem.setFile_id(file_id);
		selectedItem.setFile_name(file_name);
		selectedItem.setFile_path(file_path);
		selectedItem.setFile_format(file_format);
		File pdffile = new File(file_content_location);
		FileInputStream fis = new FileInputStream(pdffile);
		byte[] arr = new byte[(int)pdffile.length()];
		fis.read(arr);
		fis.close();
		selectedItem.setFile_content(arr);
		return Optional.of(selectedItem);
	}
}
