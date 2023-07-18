package com.projet.edp;

import java.io.File;
import java.io.FileInputStream;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.MyFile;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(FileDAO repository) {

		return args -> {
			log.info("clean database ");repository.deleteAll();
			MyFile file = new MyFile();
			file.setFile_name("Dans mon île");
			file.setFile_path("/home/");
			file.setFile_format("pdf");
			File pdffile = new File("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
			FileInputStream fis = new FileInputStream(pdffile);
			byte[] arr = new byte[(int)pdffile.length()];
			fis.read(arr);
			fis.close();
			
			file.setFile_content(arr);
 
			log.info("Preloading " + repository.save(file));

		};
	}

}
