package com.projet.edp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.service.FileContentService;
import com.projet.edp.fileViewer.service.FileService;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(FileService fileService,FileContentService fileContentService) {

		return args -> {
			log.info("clean database ");
			fileService.deleteAll();
			byte[] binaryArray = fileContentService.convertInputFileToBinaryArray("C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf");
			FileContent fileContent = new FileContent(binaryArray);
			fileContentService.save(fileContent);
			MyFile selectedItem = new MyFile("/home/","Dans mon île", "pdf", "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf", fileContent);
			log.info("Preloading " + fileService.save(selectedItem) );
		};
	}

}
