package com.zimttech.diabeticscreening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class DiabeticScreeningApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiabeticScreeningApplication.class, args);
    }

}
