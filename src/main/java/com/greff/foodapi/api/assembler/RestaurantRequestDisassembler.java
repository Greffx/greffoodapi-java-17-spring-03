package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.RestaurantRequest;
import com.greff.foodapi.domain.mapper.RestaurantMapper;
import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestaurantRequestDisassembler {
    //class used to transform model representation to domain object, that's why disassembler name, revert assembler

    private final RestaurantMapper restaurantMapper;

    public Restaurant toDomainObject(RestaurantRequest restaurantRequest) {
        return restaurantMapper.fromRestaurantRequestToRestaurant(restaurantRequest);
    }

    public void updateToDomainObject(RestaurantRequest restaurantRequest, Restaurant restaurant) {
        //Resolved [org.springframework.orm.jpa.JpaSystemException: identifier of an instance of com.greff.foodapi.domain.model.Kitchen was altered from 2 to 1]
        //this problem happens when trying to copy something, JPA understands that we are trying to transform kitchen instance id 1 to id 2.
        //but we're only trying to change instance reference of kitchen in restaurant
        //solution can be a new instance of kitchen, clean one, JPA never managed this one, can change their id, because this instance id don't exist
        //same works to city

        if (restaurant.getKitchen() != null) restaurant.setKitchen(new Kitchen());
        if (restaurant.getAddress() != null) restaurant.getAddress().setCity(new City());

        restaurantMapper.updateDomainObject(restaurantRequest, restaurant);
    }
}
