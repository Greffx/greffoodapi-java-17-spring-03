package com.greff.foodapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//need to connect to web server, this annotation 'webEnvironment' raise a web server just for those tests
//RANDOM_PORT raises a real web server, but with random port
@TestPropertySource("/application-test.properties") //will use those properties instead of normal application properties
class KitchenServiceIT {

    @LocalServerPort //since port will be random, this annotation helps to get that and use the right way
    private int port;

    @Autowired
    private Flyway flyway;

    @BeforeEach //a method to do something before each test method runs
    public void setUp() {
        RestAssured.port = port; //preparing this one here, so this class don't have so much duplicated code
        RestAssured.basePath = "/kitchens"; //same here

        flyway.migrate(); //will migrate for each method, so will clean with that callback, afterMigrate
        //like reset database everytime for this class
    }

    @Test
    void shouldReturnStatusCode200WhenGettingKitchens() {
        given()
            //saying which type of content will accept with restAssured import, like which type will return, JSON or XML or something like that
                .accept(ContentType.JSON).
        when()
            //which type will be, like get,post, delete, put, patch
                .get().
        then()
            //and response that should return
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldContain3KitchensWhenGettingKitchens() {
        given()
                .accept(ContentType.JSON).
        when()
                .get().
        then()
                .body("", hasSize(3)) //matchers has many methods with correspondence rules
                .body("name", hasItems("Indian", "Japanese", "Brazilian")); //this one compare property name value, if that array value 'name' has those
    }

    @Test
    void shouldReturn201StatusWhenRegisterAKitchen() {
        given()
                .body("{ \"name\": \"Chinese \" }") //passing a JSON, so need to be "name", that's why "\name\", to accept this ""
                .contentType(ContentType.JSON) //which type of content is passing, like JSON or XML, similar to accept, but this one is requesting, it's given to us
                .accept(ContentType.JSON).
        when()
                .post().
        then()
                .statusCode(HttpStatus.CREATED.value());
    }

}
