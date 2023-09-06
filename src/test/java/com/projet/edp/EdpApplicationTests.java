package com.projet.edp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projet.edp.fileTree.ui.FileViewerController;

@SpringBootTest
class EdpApplicationTests {



	@Autowired
	private FileViewerController fileController;

	@Test
	void contextLoads() {
		assertThat(fileController).isNotNull();
	}

}
