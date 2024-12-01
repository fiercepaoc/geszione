package com.unisp.gestioneunisp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestioneUnispApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestioneUnispApplication.class, args);
    }
}