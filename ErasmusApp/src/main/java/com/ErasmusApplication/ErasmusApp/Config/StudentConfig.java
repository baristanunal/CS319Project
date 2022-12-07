package com.ErasmusApplication.ErasmusApp.Config;

import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.UserClassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserClassRepository repository){
        return args -> {
            Student newUser1 = new Student(
                    "user1@hotmail.com",
                    "ahmet",
                    "sahin",
                    21900900,
                    "CS",
                    "3.semester",
                    "11.11.2000",
                    "TR",
                    "male"

            );

            Student newUser2 = new Student(
                    "user2@hotmail.com",
                    "murat",
                    "kartal",
                    19800800,
                    "CS",
                    "2.semester",
                    "11.11.2001",
                    "TR",
                    "female"
            );

            repository.saveAll(
                    List.of(newUser1,newUser2)
            );
        };
    }
}

