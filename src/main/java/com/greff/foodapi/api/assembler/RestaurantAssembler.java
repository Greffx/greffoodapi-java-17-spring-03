package com.greff.foodapi.api.assembler; //assembler is like a builder, converter, transformer
//package used to transform entity to model representation

import com.greff.foodapi.api.model.response.RestaurantResponse;
import com.greff.foodapi.domain.mapper.RestaurantMapper;
import com.greff.foodapi.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component //to work, needs to be a spring component
public class RestaurantAssembler {

    private final RestaurantMapper restaurantMapper;

    public RestaurantResponse toModel(Restaurant restaurant) {
        return restaurantMapper.fromRestaurantToRestaurantResponse(restaurant);
    }

    public List<RestaurantResponse> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::toModel).toList();
    }
}
