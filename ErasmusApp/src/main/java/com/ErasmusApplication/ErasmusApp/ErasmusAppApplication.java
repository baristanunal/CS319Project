package com.ErasmusApplication.ErasmusApp;


import com.ErasmusApplication.ErasmusApp.Models.Role;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Properties.FileStorageProperties;
import com.ErasmusApplication.ErasmusApp.Services.UserClassService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ErasmusAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErasmusAppApplication.class, args);
	}

//	@Bean
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
