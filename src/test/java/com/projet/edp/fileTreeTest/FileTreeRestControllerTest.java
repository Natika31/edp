package com.projet.edp.fileTreeTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.projet.edp.fileTree.domain.FileTreeItem;
import com.projet.edp.fileTree.service.FileTreeService;
import com.projet.edp.fileTree.ui.DirectoryController;
import com.projet.edp.fileTree.ui.FileTreeController;

@WebMvcTest(FileTreeController.class)
class FileTreeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileTreeService fileTreeService;

	@Test
	void GivenSelectedDirectory_whenRequestGETEmptyDirectoryIdEquals1_thenGetStoredDirectoryIdEquals1() throws Exception {
		FileTreeItem emptyCompositeItem = new FileTreeItem("/home/", "home");
		emptyCompositeItem.setItem_id(1L);
		when(fileTreeService.findFileTreeItemById(1L)).thenReturn(Optional.of(emptyCompositeItem));
		this.mockMvc.perform(get("/api/file-tree?item_id=1")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("item_id\":1,\"item_local_path\":\"/home/\",\"item_name\":\"home\"")))
		;
	}

}
