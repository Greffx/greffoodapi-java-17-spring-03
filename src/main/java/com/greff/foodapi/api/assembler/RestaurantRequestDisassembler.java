package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.RestaurantRequest;
import com.greff.foodapi.domain.mapper.RestaurantMapper;
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
}
