package com.greff.foodapi.restaurant;

import com.greff.foodapi.dummies.RestaurantDummy;
import com.greff.foodapi.helpers.ReadFileJSONTest;
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

import java.io.IOException;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RestaurantControllerIT {

    public static final long ID_NONEXISTENT = 100L;
    public static final String ID_WITH_LETTER = "100a";


    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        basePath = "/restaurants";

        flyway.migrate();
    }

    @Test
    void shouldReturn200StatusWhenGettingRestaurantById() {
        given()
                .pathParam("restaurantId", RestaurantDummy.instanceOfRestaurantDummy().getId())
                .accept(ContentType.JSON).
                when()
                .get("{restaurantId}").
                then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(RestaurantDummy.instanceOfRestaurantDummy().getName()));
    }

    @Test
    void shouldReturn404StatusWhenGettingRestaurantById() {
        given()
                .pathParam("restaurantId", ID_NONEXISTENT)
                .accept(ContentType.JSON).
                when()
                .get("{restaurantId}").
                then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn400StatusWhenGettingRestaurantByIdWrongWith() {
        given()
                .pathParam("restaurantId", ID_WITH_LETTER)
                .accept(ContentType.JSON).
                when()
                .get("{restaurantId}").
                then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturn400StatusWhenGettingRestaurantByIdWithWrongURL() {
        given()
                .basePath("/restasss")
                .pathParam("restaurantId", RestaurantDummy.instanceOfRestaurantDummy().getId())
                .accept(ContentType.JSON).
                when()
                .get("{restaurantId}").
                then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }


    @Test
    void shouldReturn200StatusWhenGettingRestaurants() {
        given()
                .accept(ContentType.JSON).
                when()
                .get().
                then()
                .statusCode(HttpStatus.OK.value())
                .body("", hasSize(6));
    }

    @Test
    void shouldReturn404StatusWhenGettingRestaurantsWithWrongURL() {
        given()
                .basePath("/resadsd")
                .accept(ContentType.JSON).
                when()
                .get().
                then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn201StatusWhenCreatingRestaurant() throws IOException {
        var restaurantRequest = ReadFileJSONTest.restaurantJsonFileReader();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(restaurantRequest).
                when()
                .post().
                then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo(RestaurantDummy.instanceOfRestaurantDummy().getName()));
    }

    @Test
    void shouldReturn400StatusWhenCreatingRestaurantWithoutAllProperties() throws IOException {
        var restaurantRequest = ReadFileJSONTest.restaurantNullFieldJsonFileReader();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(restaurantRequest).
                when()
                .post().
                then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturn405StatusWhenCreatingRestaurantWithId() throws IOException {
        var restaurantRequest = ReadFileJSONTest.restaurantJsonFileReader();

        given()
                .pathParam("restaurantId", RestaurantDummy.instanceOfRestaurantDummy().getId())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(restaurantRequest).
                when()
                .post("{restaurantId}").
                then()
                .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }
}
