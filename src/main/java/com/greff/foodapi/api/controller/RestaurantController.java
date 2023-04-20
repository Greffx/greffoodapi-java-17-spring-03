package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.usecase.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @GetMapping("/search/tax/")
    public ResponseEntity<List<Restaurant>> findByTax(BigDecimal lower, BigDecimal higher) {
        return ResponseEntity.ok(restaurantService.findByDeliveryTax(lower, higher));
    }

    @GetMapping("/search/name/kitchen-id/")
    public ResponseEntity<List<Restaurant>> findByNameAndKitchen(String name, Long kitchenId) {
        return ResponseEntity.ok(restaurantService.findByNameAndKitchen(name, kitchenId));
    }

    @GetMapping("/search/first-by-name/")
    public ResponseEntity<Restaurant> findFirstOneByName(String name) {
        return ResponseEntity.ok(restaurantService.findFirstOneByName(name));
    }

    @GetMapping("/search/top-two-by-name/")
    public ResponseEntity<List<Restaurant>> topTwoRestaurantsByName(String name) {
        return ResponseEntity.ok(restaurantService.findTwoRestaurantsByName(name));
    }

    @GetMapping("/search/how-many-restaurants-per-kitchen-id/")
    public ResponseEntity<Integer> howManyRestaurantsPerKitchen(Long kitchenId) {
        return ResponseEntity.ok(restaurantService.findHowManyRestaurantsPerKitchen(kitchenId));
    }

    @GetMapping("/search/restaurants-with-free-delivery-tax/")
    public List<Restaurant> findWithFreeDeliveryTaxAndWithSimilarName(String name) {
        return restaurantService.findWithFreeDeliveryTaxAndWithSimilarName(name);
    }

    @PostMapping
//? is a wildcard, means that can return anything, because of the 38 line, that's a string type, só ? will help with that
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant, UriComponentsBuilder builder) {
        Restaurant restaurant1 = restaurantService.create(restaurant);
        return ResponseEntity.created(builder.path("/{id}").buildAndExpand(restaurant1.getId()).toUri()).body(restaurant1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long id) {
        restaurantService.findById(id);
        return ResponseEntity.ok(restaurantService.update(restaurant, id));
    }

    @PatchMapping("/{id}")
    //map to 'PATCH' endpoint, which means that don't need to update everything, like 'PUT' type, that needs everything
    public ResponseEntity<Restaurant> patchRestaurant(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
        //Map<String, Object> string is key like 'NAME', 'TAX', 'KITCHEN', and Object is value of key, like 'RESTAURANT NAME', 'VALUE OF TAX' and 'KITCHEN NAME'
        //Will only map values that will come of request body, so user can work with attributes that he wants to alter.This means that he must work only with things that he wants to work
        Restaurant restaurant = restaurantService.findById(id);

        restaurantService.patchFields(fields, restaurant);

        return updateRestaurant(restaurant, id);
    }
}
