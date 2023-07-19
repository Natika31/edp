package com.projet.edp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.MyFile;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	private MyFile createTestSelectedItem(Long file_id, String file_path, String file_name, String file_format, String file_content_location) throws FileNotFoundException, IOException {
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
		return selectedItem;
	}
	@Bean
	CommandLineRunner initDatabase(FileDAO fileDao) {

		return args -> {
			log.info("clean database ");
			fileDao.deleteAll();
			MyFile selectedItem = createTestSelectedItem(1L,"/home/","Dans mon île", "pdf", "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
			log.info("Preloading " + fileDao.save(selectedItem));

		};
	}

}
