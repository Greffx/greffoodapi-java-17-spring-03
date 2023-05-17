package com.greff.foodapi;

import com.greff.foodapi.infrastructure.repository.impl.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
//EnableJpaRepositories is saying = should be used as the base class for all your repository implementations
//This means that all repositories application will extend CustomJpaRepositoryImpl and inherit its functionality
//including the custom methods or overrides you have defined in that class
public class GreffoodApiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); //changing timezone of application to UTC type, offset here will be Z, that means +00:00
        SpringApplication.run(GreffoodApiApplication.class, args);
    }
}
