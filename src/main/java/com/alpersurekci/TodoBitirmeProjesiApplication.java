package com.alpersurekci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoBitirmeProjesiApplication {

    public static void main(String[] args) {

        SpringApplication.run(TodoBitirmeProjesiApplication.class, args);


        System.setProperty("spring.devtools.restart.enabled", "false");

        System.setProperty("java.awt.headless", "false");
    }

}
