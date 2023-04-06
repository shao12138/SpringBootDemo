package com.nudt.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Scheduled2Application {

    public static void main(String[] args) {
        SpringApplication.run(Scheduled2Application.class, args);
    }

}
