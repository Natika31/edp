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

import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.service.FileContentService;
import com.projet.edp.fileViewer.service.FileService;
import com.projet.edp.fileViewer.ui.FileViewerController;

@WebMvcTest(FileViewerController.class)
class FileRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileContentService fileContentService;

	@MockBean
	private FileService fileService;

	@Test
	void GivenSelectedFile_whenRequestGETFileIdEquals1_thenGetStoredFileIdEquals1() throws Exception {

		Optional<MyFile> selectedItem = createFile("/home/","Dans mon île", "pdf", "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
		when(fileService.findFileById(1L)).thenReturn(selectedItem);
		this.mockMvc.perform(get("/api/file?file_id=1")).andDo(print()).andExpect(status().isOk())
		//TODO: encodage pb 
		.andExpect(content().string(containsString("\"file_destination_path\":\"/home/\",\"file_name\":\"Dans mon île\",\"file_format\":\"pdf\",\"binary_content\":\"JVBERi0xLjcKCjQgMCBvYmoKKElkZW50aXR5KQplbmRvYmoKNSAwIG9iagooQWRvYmUpCmVuZG9iago4IDAgb2JqCjw8Ci9GaWx0ZXIgL0ZsYXRlRGVjb2RlCi9")))
		;

	}
//TODO: mock convertInputFileToBinaryArray to not return null
	private Optional<MyFile> createFile(String file_path, String file_name, String file_format, String file_origin_path) throws FileNotFoundException, IOException {
		byte[] binaryArray = fileContentService.convertInputFileToBinaryArray(file_origin_path);			
		FileContent fileContent = new FileContent(binaryArray);
		fileContent.setFile_content_id(1L);
		MyFile selectedItem = new MyFile(file_path, file_name, file_format, file_origin_path, fileContent);
		selectedItem.setFile_id(1L);
		return Optional.of(selectedItem);
	}
}
