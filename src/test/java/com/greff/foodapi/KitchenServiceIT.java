package com.greff.foodapi;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//need to connect to web server, this annotation 'webEnvironment' raise a web server just for those tests
//RANDOM_PORT raises a real web server, but with random port
class KitchenServiceIT {

    @LocalServerPort //since port will be random, this annotation helps to get that and use the right way
    private int port;

    @Test
    void shouldReturnStatusCode200WhenGettingKitchens() {
        given()
            .basePath("/kitchens")
            .port(port)
            //saying which type of content will accept with restAssured import, like which type will return, JSON or XML or something like that
            .accept(ContentType.JSON)
        .when()
            //which type will be, like get,post, delete, put, patch
            .get()
        .then()
            //and response that should return
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldContain3KitchensWhenGettingKitchens() {
        given()
                .basePath("/kitchens")
                .port(port)
                //saying which type of content will accept with restAssured import, like which type will return, JSON or XML or something like that
                .accept(ContentType.JSON)
                .when()
                //which type will be, like get,post, delete, put, patch
                .get()
                .then()
                //and response that should return
                .statusCode(HttpStatus.OK.value());
    }

}
