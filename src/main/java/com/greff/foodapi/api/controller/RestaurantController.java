package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant, UriComponentsBuilder builder) { //? is a wildcard, means that can return anything, because of the 38 line, that's a string type, só ? will help with that
        try {
            Restaurant restaurant1 = restaurantService.create(restaurant);
            return ResponseEntity.created(builder.path("/{id}").buildAndExpand(restaurant1.getId()).toUri()).body(restaurant1);
        } catch (NotFoundObjectException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long id) {
        try {
            restaurantService.findById(id);
        } catch (NotFoundObjectException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        try {
            return ResponseEntity.ok(restaurantService.update(restaurant, id));
        } catch (NotFoundObjectException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    //map to 'PATCH' endpoint, which means that don't need to update everything, like 'PUT' type, that needs everything
    public ResponseEntity<?> patchRestaurant(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
        //Map<String, Object> string is key like 'NAME', 'TAX', 'KITCHEN', and Object is value of key, like 'RESTAURANT NAME', 'VALUE OF TAX' and 'KITCHEN NAME'
        //Will only map values that will come of request body, so user can work with attributes that he wants to alter.This means that he must work only with things that he wants to work
        Restaurant restaurant = restaurantService.findById(id);

        if (restaurant == null) return ResponseEntity.notFound().build();

        restaurantService.patchFields(fields, restaurant);

        return updateRestaurant(restaurant, id);
    }
}
