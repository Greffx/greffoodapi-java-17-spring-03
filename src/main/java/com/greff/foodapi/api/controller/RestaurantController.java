package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.usecase.RestaurantService;
 import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
            return ResponseEntity.ok(restaurantService.update(restaurant, id));
        } catch (NotFoundObjectException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
