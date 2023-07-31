package com.projet.edp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.projet.edp.directoryViewer.domain.Directory;
import com.projet.edp.directoryViewer.service.DirectoryService;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.service.FileContentService;
import com.projet.edp.fileViewer.service.FileService;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(FileService fileService,FileContentService fileContentService, DirectoryService directoryService) {

		return args -> {
			log.info("clean database ");
			fileService.deleteAll();
			directoryService.deleteAll();
			//get an input file binary content 
			String file_origin_path = "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf";
			byte[] binaryArray = fileContentService.convertInputFileToBinaryArray(file_origin_path);
			FileContent fileContent = new FileContent(binaryArray);
			//save binary content
			fileContentService.save(fileContent);
			//Create a new file 
			MyFile selectedFile = new MyFile("/home/Dans mon île.pdf","Dans mon île", "pdf", file_origin_path, fileContent);
			//save the new file
			log.info("Preloading " + fileService.save(selectedFile) );
			//Create a new empty directory and save it
			Directory selectedEmptyDirectory = new Directory("/home/", "home");
			log.info("Preloading " + directoryService.save(selectedEmptyDirectory));
			//Create a directory containing one file and save it
			Directory selectedDirectory = new Directory("/home/", "home");
			selectedDirectory.addChildren(selectedFile);
			log.info("Preloading " + directoryService.save(selectedDirectory));
		};
	}

}
