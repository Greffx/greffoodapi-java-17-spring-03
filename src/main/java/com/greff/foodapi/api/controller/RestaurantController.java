package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.usecase.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<Restaurant> findAll() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant findById(@PathVariable Long id) {
        return restaurantService.findById(id);
    }

    @GetMapping("/search/tax/")
    public List<Restaurant> findByTax(BigDecimal lower, BigDecimal higher) {
        return restaurantService.findByDeliveryTax(lower, higher);
    }

    @GetMapping("/search/name/kitchen-id/")
    public List<Restaurant> findByNameAndKitchen(String name, Long kitchenId) {
        return restaurantService.findByNameAndKitchen(name, kitchenId);
    }

    @GetMapping("/search/first-by-name/")
    public Restaurant findFirstOneByName(String name) {
        return restaurantService.findFirstOneByName(name);
    }

    @GetMapping("/search/top-two-by-name/")
    public List<Restaurant> topTwoRestaurantsByName(String name) {
        return restaurantService.findTwoRestaurantsByName(name);
    }

    @GetMapping("/search/how-many-restaurants-per-kitchen-id/")
    public Integer howManyRestaurantsPerKitchen(Long kitchenId) {
        return restaurantService.findHowManyRestaurantsPerKitchen(kitchenId);
    }

    @GetMapping("/search/restaurants-with-free-delivery-tax/")
    public List<Restaurant> findWithFreeDeliveryTaxAndWithSimilarName(String name) {
        return restaurantService.findWithFreeDeliveryTaxAndWithSimilarName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //? is a wildcard, means that can return anything, because of the 38 line, that's a string type, só ? will help with that
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant, UriComponentsBuilder builder) {
        return restaurantService.create(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long id) {
        return restaurantService.update(restaurant, id);
    }

    @PatchMapping("/{id}")
    //map to 'PATCH' endpoint, which means that don't need to update everything, like 'PUT' type, that needs everything
    public Restaurant patchRestaurant(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
        //Map<String, Object> string is key like 'NAME', 'TAX', 'KITCHEN', and Object is value of key, like 'RESTAURANT NAME', 'VALUE OF TAX' and 'KITCHEN NAME'
        //Will only map values that will come of request body, so user can work with attributes that he wants to alter.This means that he must work only with things that he wants to work
        Restaurant restaurant = restaurantService.findById(id);

        restaurantService.patchFields(fields, restaurant);

        return updateRestaurant(restaurant, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteById(id);
    }
}
