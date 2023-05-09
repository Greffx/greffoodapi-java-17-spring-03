package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.RestaurantRequest;
import com.greff.foodapi.api.model.response.RestaurantResponse;
import com.greff.foodapi.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring") //@Mapper - annotation tha marks interface as mapping interface to spring
@Service
public interface RestaurantMapper {

    //since those objects got different names, need to map target and source to change values
    @Mapping(target = "kitchenResponse", source = "kitchen")
    RestaurantResponse fromRestaurantToRestaurantResponse(Restaurant restaurant); //method to transform entity to DTO representation

    @Mapping(target = "kitchen.id", source = "kitchenIdRefRequest.id")
    Restaurant fromRestaurantRequestToRestaurant(RestaurantRequest restaurant);

    @Mapping(target = "kitchen.id", source = "kitchenIdRefRequest.id")
    Restaurant updateDomainObejct(RestaurantRequest restaurantRequest, @MappingTarget Restaurant restaurant);
    //Declares a parameter of a mapping method to be the target of the mapping.
    //Not more than one parameter can be declared as MappingTarget.
}
