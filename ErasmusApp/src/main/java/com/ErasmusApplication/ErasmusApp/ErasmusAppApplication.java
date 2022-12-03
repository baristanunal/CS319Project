package com.ErasmusApplication.ErasmusApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ErasmusAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErasmusAppApplication.class, args);
	}
	@RequestMapping("/h")
	public String h(){
		return  "hello";
	}
}
