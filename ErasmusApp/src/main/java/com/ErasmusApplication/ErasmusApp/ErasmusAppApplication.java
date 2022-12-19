package com.ErasmusApplication.ErasmusApp;


import com.ErasmusApplication.ErasmusApp.Models.HostUniversity;
import com.ErasmusApplication.ErasmusApp.Models.HostUniversityDepartment;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Properties.FileStorageProperties;
import com.ErasmusApplication.ErasmusApp.Repositories.UserClassRepository;
import com.ErasmusApplication.ErasmusApp.Services.*;
import com.itextpdf.text.DocumentException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ErasmusAppApplication {
  private final UserClassRepository userClassRepository;

  public ErasmusAppApplication(UserClassRepository userClassRepository) {
    this.userClassRepository = userClassRepository;
  }

  public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {
		SpringApplication.run(ErasmusAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationService applicationService, UserClassService userClassService, HostUniversityService hostUniversityService, HostUniversityDepartmentService hostUniversityDepartmentService ) {
		return args -> {

      // Add students to the database.
      UserClass user0 = new UserClass( "Baris Tan", "Unal", "22000000", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user0 );
      UserClass user1 = new UserClass( "Yusuf", "Senyuz", "22000001", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user1 );
      UserClass user2 = new UserClass( "Ahmet", "Sahin", "22000002", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user2 );
      UserClass user3 = new UserClass( "Kaan Berk", "Kabadayi", "22000003", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user3 );
      UserClass user4 = new UserClass( "Ugur Can", "Altun", "22000004", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user4 );
      UserClass user5 = new UserClass( "Meric", "Serezli", "22000005", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user5 );
      UserClass user6 = new UserClass( "Defne", "Yildirim", "22000006", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user6 );
      UserClass user7 = new UserClass( "Bora", "Akdari", "22000007", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user7 );
      UserClass user8 = new UserClass( "Melis", "Bakan", "22000008", "ENG", "CS", "student", "name.surname@ug.bilkent.edu.tr", "password" );
      userClassService.saveUser( user8 );


      // Add host universities to the database.
      List<HostUniversityDepartment> hostUniversityDepartments0 = new ArrayList<>();
      HostUniversityDepartment department0 = new HostUniversityDepartment( "CS", 1 );
      hostUniversityDepartments0.add(department0);
      HostUniversity hostUniversity0 = new HostUniversity( "EPF", hostUniversityDepartments0  );
      hostUniversityService.saveHostUni( hostUniversity0 );

      List<HostUniversityDepartment> hostUniversityDepartments1 = new ArrayList<>();
      HostUniversityDepartment department1 = new HostUniversityDepartment( "CS", 2 );
      hostUniversityDepartments1.add(department1);
      HostUniversity hostUniversity1 = new HostUniversity( "Vrije University", hostUniversityDepartments1  );
      hostUniversityService.saveHostUni( hostUniversity1 );

      List<HostUniversityDepartment> hostUniversityDepartments2 = new ArrayList<>();
      HostUniversityDepartment department2 = new HostUniversityDepartment( "CS", 1 );
      hostUniversityDepartments2.add(department2);
      HostUniversity hostUniversity2 = new HostUniversity( "Roskilde University", hostUniversityDepartments2  );
      hostUniversityService.saveHostUni( hostUniversity2 );

      List<HostUniversityDepartment> hostUniversityDepartments3 = new ArrayList<>();
      HostUniversityDepartment department3 = new HostUniversityDepartment( "CS", 1 );
      hostUniversityDepartments3.add(department3);
      HostUniversity hostUniversity3 = new HostUniversity( "ESIEA", hostUniversityDepartments3  );
      hostUniversityService.saveHostUni( hostUniversity3 );

      List<HostUniversityDepartment> hostUniversityDepartments4 = new ArrayList<>();
      HostUniversityDepartment department4 = new HostUniversityDepartment( "CS", 1 );
      hostUniversityDepartments4.add(department4);
      HostUniversity hostUniversity4 = new HostUniversity( "ESIEE Paris", hostUniversityDepartments4  );
      hostUniversityService.saveHostUni( hostUniversity4 );

      List<HostUniversityDepartment> hostUniversityDepartments5 = new ArrayList<>();
      HostUniversityDepartment department5 = new HostUniversityDepartment( "CS", 3 );
      hostUniversityDepartments5.add(department5);
      HostUniversity hostUniversity5 = new HostUniversity( "Saarland University", hostUniversityDepartments5  );
      hostUniversityService.saveHostUni( hostUniversity5 );

      List<HostUniversityDepartment> hostUniversityDepartments6 = new ArrayList<>();
      HostUniversityDepartment department6 = new HostUniversityDepartment( "CS", 1 );
      hostUniversityDepartments6.add(department6);
      HostUniversity hostUniversity6 = new HostUniversity( "AGH University of Science and Technology", hostUniversityDepartments6  );
      hostUniversityService.saveHostUni( hostUniversity6 );

      List<HostUniversityDepartment> hostUniversityDepartments7 = new ArrayList<>();
      HostUniversityDepartment department7 = new HostUniversityDepartment( "CS", 2 );
      hostUniversityDepartments7.add(department7);
      HostUniversity hostUniversity7 = new HostUniversity( "Kingston University", hostUniversityDepartments7  );
      hostUniversityService.saveHostUni( hostUniversity7 );

      List<HostUniversityDepartment> hostUniversityDepartments8 = new ArrayList<>();
      HostUniversityDepartment department8 = new HostUniversityDepartment( "CS", 1 );
      hostUniversityDepartments8.add(department8);
      HostUniversity hostUniversity8 = new HostUniversity( "Universita degli Studi di LAquila", hostUniversityDepartments8  );
      hostUniversityService.saveHostUni( hostUniversity8 );

      List<HostUniversityDepartment> hostUniversityDepartments9 = new ArrayList<>();
      HostUniversityDepartment department9 = new HostUniversityDepartment( "EEE", 5 );
      hostUniversityDepartments9.add(department9);
      HostUniversity hostUniversity9 = new HostUniversity( "Vienna University", hostUniversityDepartments9  );
      hostUniversityService.saveHostUni( hostUniversity9 );

//			Application a = new Application();
//			a.setApplicationType("Erasmus");
//			a.setPlaced(false);
//			long id = 1;
//			studentService.addApplicationToStudent(id,a);
//
//			PreApproval preApproval = new PreApproval();
//			preApproval.setPreApprovalDeadline("11/11/1111");
//			applicationService.addPreApproval(id, preApproval);

//			System.out.println(studentService.getApplicationByApplicationId(id, id));
//			long p = 2;
//			System.out.println(applicationService.getPreApproval(p));

		};
	}

// 	@Bean
//	CommandLineRunner run(UserClassService userClassService){
//		return args -> {
//			userClassService.saveRole(new Role(null,"ROLE_USER"));
//			userClassService.saveRole(new Role(null,"ROLE_STUDENT"));
//			userClassService.saveRole(new Role(null,"ROLE_COURSE_COORDINATOR"));
//			userClassService.saveRole(new Role(null,"ROLE_ADMIN"));
//
//			userClassService.saveUser(new UserClass("@","ali","çakar","22000000","Engineering","EEE","a123"));
//			userClassService.saveUser(new UserClass("@","mehmet","basan","18000000","Engineering","CS","a123"));
//			userClassService.saveUser(new UserClass("@","murat","alık","17000000","Engineering","CS","a123"));
//
//			userClassService.addRoleToUser("22000000","ROLE_USER");
//			userClassService.addRoleToUser("18000000","ROLE_USER");
//			userClassService.addRoleToUser("18000000","ROLE_ADMIN");
//			userClassService.addRoleToUser("17000000","ROLE_USER");
//			userClassService.addRoleToUser("17000000","ROLE_COURSE_COORDINATOR");
//
//
//		};
//	}

	@RequestMapping("/h")
	public String h(){
		return  "hello";
	}

}
