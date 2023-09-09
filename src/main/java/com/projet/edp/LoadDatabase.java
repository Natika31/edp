package com.projet.edp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projet.edp.fileTree.domain.Directory;
import com.projet.edp.fileTree.service.DirectoryService;
import com.projet.edp.fileViewer.domain.FileContent;
import com.projet.edp.fileViewer.domain.MyFile;
import com.projet.edp.fileViewer.service.FileContentService;
import com.projet.edp.fileViewer.service.FileService;
import com.projet.edp.group.domain.MyGroup;
import com.projet.edp.group.service.GroupService;
import com.projet.edp.user.domain.MyUser;
import com.projet.edp.user.service.UserService;


@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(FileService fileService,FileContentService fileContentService, DirectoryService directoryService,UserService userService, GroupService groupService) {

		return args -> {
			log.info("clean database ");
			fileService.deleteAll();
			directoryService.deleteAll();
			userService.deleteAll();
			groupService.deleteAll();

			//toto account
			//get an input file binary content 
			String file_origin_path = "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf";
			FileContent fileContent = new FileContent();
			byte[] binaryArray = fileContent.convertInputFileToBinaryArray(file_origin_path);
			fileContent.setBinary_content(binaryArray);
			//save binary content
			log.info("Preloading " + fileContentService.save(fileContent) );

			//Create a new file 
			MyFile childFile = new MyFile("Dans mon ile", "/home/Dans_mon_ile.pdf", "pdf", file_origin_path, fileContent);
			//save the new file
			log.info("Preloading " + fileService.save(childFile) );

			Directory dir21 = new Directory("Henri Salvador","/home/natacha/henri_salvador");
			dir21.addChildren(childFile);
			log.info("Preloading " + directoryService.save(dir21));

			Directory dir22 = new Directory("The Beatles","/home/natacha/the_beatles");
			log.info("Preloading " + directoryService.save(dir22));
			
			Directory dir23 = new Directory("Johnny","/home/natacha/johnny");
			log.info("Preloading " + directoryService.save(dir23));

			Directory dir11 = new Directory("Natacha","/home/natacha" );
			dir11.addChildren(dir21);
			dir11.addChildren(dir22);
			log.info("Preloading " + directoryService.save(dir11));

			Directory dir12 = new Directory("Jack","/home/jack");
			dir12.addChildren(dir23);
			log.info("Preloading " + directoryService.save(dir12));

			//Create a root directory 
			Directory rootDirectory = new Directory("home", "/home");	

			rootDirectory.addChildren(dir11);
			rootDirectory.addChildren(dir12);

			log.info("Preloading " + directoryService.save(rootDirectory));
			//Create an user 
			MyUser myUser1 = new MyUser("toto", "toto@me", rootDirectory);
			log.info("Preloading " + userService.save(myUser1));

			//tata account
			//get an input file binary content 
			String file_origin_path1 = "C:/Users/Natacha/Documents/cnam/GLG204 - 2023/facture kine.pdf";
			FileContent fileContent1 = new FileContent();
			byte[] binaryArray1 = fileContent1.convertInputFileToBinaryArray(file_origin_path1);
			fileContent1.setBinary_content(binaryArray1);
			//save binary content
			log.info("Preloading " + fileContentService.save(fileContent1) );

			//Create a new file 
			MyFile childFile1 = new MyFile("Dans mon île", "/home/ori/sante/kine/facture kine.pdf", "pdf", file_origin_path1, fileContent1);
			//save the new file
			log.info("Preloading " + fileService.save(childFile1) );

			Directory dir211 = new Directory("kiné","/home/ori/santé/kiné");
			dir211.addChildren(childFile1);
			log.info("Preloading " + directoryService.save(dir211));
			
			Directory dir231 = new Directory("vision","/home/ori/santé/vision");
			log.info("Preloading " + directoryService.save(dir231));

			Directory dir121 = new Directory("audition","/home/ori/santé/audition");
			log.info("Preloading " + directoryService.save(dir121));
			
			Directory dir221 = new Directory("santé","/home/ori/santé");
			dir221.addChildren(dir211);
			dir221.addChildren(dir231);
			dir221.addChildren(dir121);
			log.info("Preloading " + directoryService.save(dir221));
		

			Directory dir111 = new Directory("ori","/home/ori" );
			dir111.addChildren(dir221);
			log.info("Preloading " + directoryService.save(dir111));

			//Create a root directory 
			Directory rootDirectory1 = new Directory("home", "/home");	
			rootDirectory1.addChildren(dir111);

			log.info("Preloading " + directoryService.save(rootDirectory1));
			//Create an user 
			MyUser myUser2 = new MyUser("tata", "tata@me", rootDirectory1);
			log.info("Preloading " + userService.save(myUser2));
			
			//create  groups
			MyGroup group1= new MyGroup("famille");
			log.info("Preloading " + groupService.save(group1));

			MyGroup group2= new MyGroup("travail");
			group2.addMember(myUser1);
			group2.addMember(myUser2);
			log.info("Preloading " + groupService.save(group2));

		};
	}

}