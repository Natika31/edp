package com.projet.edp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projet.edp.fileViewer.dao.FileDAO;
import com.projet.edp.fileViewer.domain.File;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(FileDAO repository) {

		return args -> {
			log.info("clean database ");repository.deleteAll();

			log.info("Preloading " + repository.save(new File("/home/", "test.pdf")));
			log.info("Preloading " + repository.save(new File("/home/", "test.jpg")));
		};
	}

}
