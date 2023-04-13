package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.repository.RestaurantRepository;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import com.greff.foodapi.infrastructure.repository.spec.FreeDeliveryTaxRestaurantsSpec;
import com.greff.foodapi.infrastructure.repository.spec.RestaurantsWithSimilarNameSpec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantService restaurantService,
                                RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

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
    public List<Restaurant> howManyRestaurantsPerKitchen(String name) {
        var withFreeTax = new FreeDeliveryTaxRestaurantsSpec();//class that represents specification
        var withSimilarName = new RestaurantsWithSimilarNameSpec(name);//class that represents specification

        return restaurantRepository.findAll(withFreeTax.and(withSimilarName)); //and is a specification too, to bind specifications
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
