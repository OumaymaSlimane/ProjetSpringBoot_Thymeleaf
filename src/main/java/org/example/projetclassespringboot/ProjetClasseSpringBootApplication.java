package org.example.projetclassespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class ProjetClasseSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetClasseSpringBootApplication.class, args);
    }

}
