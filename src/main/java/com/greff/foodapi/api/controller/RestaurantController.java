package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.RestaurantAssembler;
import com.greff.foodapi.api.assembler.RestaurantRequestDisassembler;
import com.greff.foodapi.api.model.request.RestaurantRequest;
import com.greff.foodapi.api.model.response.RestaurantResponse;
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
    private final RestaurantAssembler restaurantAssembler;
    private final RestaurantRequestDisassembler restaurantRequestDisassembler;

    @GetMapping
    public List<RestaurantResponse> findAll() {
        var listRestaurants = restaurantService.findAll();

        return listRestaurants.stream().map(restaurantAssembler::toModel).toList();
    }

    @GetMapping("/{id}")
    public RestaurantResponse findById(@PathVariable Long id) {
        var restaurant = restaurantService.findById(id);

        return restaurantAssembler.toModel(restaurant);
    }

    @GetMapping("/search/tax/")
    public List<RestaurantResponse> findByTax(BigDecimal lower, BigDecimal higher) {
        var listRestaurants = restaurantService.findByDeliveryTax(lower, higher);

        return restaurantAssembler.toCollectionModel(listRestaurants);
    }

    @GetMapping("/search/name/kitchen-id/")
    public List<RestaurantResponse> findByNameAndKitchen(String name, Long kitchenId) {
        var listRestaurants = restaurantService.findByNameAndKitchen(name, kitchenId);

        return restaurantAssembler.toCollectionModel(listRestaurants);
    }

    @GetMapping("/search/first-by-name/")
    public RestaurantResponse findFirstOneByName(String name) {
        var restaurant = restaurantService.findFirstOneByName(name);

        return restaurantAssembler.toModel(restaurant);
    }

    @GetMapping("/search/top-two-by-name/")
    public List<RestaurantResponse> topTwoRestaurantsByName(String name) {
        var listRestaurants = restaurantService.findTwoRestaurantsByName(name);

        return restaurantAssembler.toCollectionModel(listRestaurants);
    }

    @GetMapping("/search/how-many-restaurants-per-kitchen-id/")
    public Integer howManyRestaurantsPerKitchen(Long kitchenId) {
        return restaurantService.findHowManyRestaurantsPerKitchen(kitchenId);
    }

    @GetMapping("/search/restaurants-with-free-delivery-tax/")
    public List<RestaurantResponse> findWithFreeDeliveryTaxAndWithSimilarName(String name) {
        var listRestaurants = restaurantService.findWithFreeDeliveryTaxAndWithSimilarName(name);

        return restaurantAssembler.toCollectionModel(listRestaurants);
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
    public RestaurantResponse createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest) {
        //since user-case is a service class of domain layer, can't know or use restaurantRequest or response DTOs classes
        //that job is for API layer, representation layer, that's why conversion happens in this layer
        var restaurant = restaurantRequestDisassembler.toDomainObject(restaurantRequest);

        restaurant = restaurantService.create(restaurant);

        return restaurantAssembler.toModel(restaurant);
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest, @PathVariable Long id) {
        var restaurant = restaurantService.findById(id);

        restaurantRequestDisassembler.updateToDomainObject(restaurantRequest, restaurant); //this is the conversion JSON to entity with new value
        restaurant = restaurantService.update(restaurant);

        return restaurantAssembler.toModel(restaurant);
    }

    //singleton resource with a sub-resource that is no-crud action, like an abstract business rule that need it to be implemented
    //something more concept, but good to be implemented, turning more real, concrete
    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restaurantActivation(@PathVariable Long id) {
        restaurantService.activation(id);
    }

    //this is like a logical delete, still exist at database
    @DeleteMapping("/{id}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restaurantDeactivation(@PathVariable Long id) {
        restaurantService.deactivation(id);
    }


    @PatchMapping("/{id}")
    //map to 'PATCH' endpoint, which means that don't need to update everything, like 'PUT' type, that needs everything
    public RestaurantResponse patchRestaurant(@RequestBody Map<String, Object> fields, @PathVariable Long id, HttpServletRequest request) {
        //Map<String, Object> string is key like 'NAME', 'TAX', 'KITCHEN',
        //and Object is value of key, like 'RESTAURANT NAME', 'VALUE OF TAX' and 'KITCHEN NAME'
        //Will only map values that will come of request body, so user can work with attributes that he wants to alter.
        // This means that he must work only with things that he wants to work
        var restaurant = restaurantService.findById(id);

        restaurantService.patchFields(fields, restaurant, request);

        return restaurantAssembler.toModel(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteById(id);
    }
}
