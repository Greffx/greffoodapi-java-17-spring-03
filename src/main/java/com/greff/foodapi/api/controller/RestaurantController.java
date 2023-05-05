package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.model.response.RestaurantResponse;
import com.greff.foodapi.domain.mapper.RestaurantMapper;
import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.usecase.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public List<RestaurantResponse> findAll() {
        return restaurantService.findAll().stream().map(restaurantMapper::fromRestaurantToRestaurantResponse).toList();
    }

    @GetMapping("/{id}")
    public RestaurantResponse findById(@PathVariable Long id) {
         return restaurantMapper.fromRestaurantToRestaurantResponse(restaurantService.findById(id));
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
    //? is a wildcard, means that can return anything, because of the 38 line,
    //that's a string type, so ? will help with that
    //@valid means that before calling this method will have a validation check of restaurant instance
    //better to do this here instead of JPA doing it
    //@Validated accept another group, because @Valid by default is a Default.class group and can't change
    //to be able to use another group in validations annotations we need to use this and choose which group it's
    //@Valid(Default.class) that is how work, @Valid don't show, just do it
    public Restaurant createRestaurant(@RequestBody @Valid Restaurant restaurant) {
        return restaurantService.create(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@RequestBody @Valid Restaurant restaurant, @PathVariable Long id) {
        return restaurantService.update(restaurant, id);
    }

    @PatchMapping("/{id}")
    //map to 'PATCH' endpoint, which means that don't need to update everything, like 'PUT' type, that needs everything
    public Restaurant patchRestaurant(@RequestBody Map<String, Object> fields, @PathVariable Long id, HttpServletRequest request) {
        //Map<String, Object> string is key like 'NAME', 'TAX', 'KITCHEN',
        //and Object is value of key, like 'RESTAURANT NAME', 'VALUE OF TAX' and 'KITCHEN NAME'
        //Will only map values that will come of request body, so user can work with attributes that he wants to alter.
        // This means that he must work only with things that he wants to work
        Restaurant restaurant = restaurantService.findById(id);

        restaurantService.patchFields(fields, restaurant, request);

        return updateRestaurant(restaurant, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteById(id);
    }
}
