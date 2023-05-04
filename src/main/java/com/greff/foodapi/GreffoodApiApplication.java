package com.greff.foodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class GreffoodApiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); //changing timezone of application to UTC type
        SpringApplication.run(GreffoodApiApplication.class, args);
    }
}
