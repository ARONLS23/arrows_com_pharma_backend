package com.arrows.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PharmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmaApplication.class, args);
    }

}
