package com.ApiBarco.ClubNauico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ApiBarco")
@EntityScan("com.ApiBarco")
@ComponentScan("com.ApiBarco")
public class ClubNauticoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubNauticoApplication.class, args);
    }


}
