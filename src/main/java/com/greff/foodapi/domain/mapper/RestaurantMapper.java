package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.RestaurantRequest;
import com.greff.foodapi.api.model.response.RestaurantResponse;
import com.greff.foodapi.domain.model.Restaurant;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {KitchenMapper.class, CityMapper.class, AddressMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
//The componentModel = "spring" attribute indicates that the generated mapper implementation class
//should be registered as a Spring component and can be autowired into other Spring components.
//'uses' This tells to include the EntityMapper.class in the generated code for the ThisMapper interface,
//so that the implementation of the method can be found and used by this class.
//unmappedTargetPolicy = ReportingPolicy.IGNORE, that means policy to just ignore target properties that got null, empty mapping or are not mapped
public interface RestaurantMapper {

    //since those objects got different names, need to map target and source to change values
    @Mapping(target = "addressResponse", source = "address", qualifiedByName = "toAddressResponse")
    @Mapping(target = "kitchenResponse", source = "kitchen", qualifiedByName = "toKitchenResponse")
    //qualifiedByName used to specify the name of a specific method that should be used to map a particular source or target property.
    //better than using several @mapping to each property
    RestaurantResponse fromRestaurantToRestaurantResponse(Restaurant restaurant); //method to transform entity to DTO representation
    //since those objects got different names, need to map target and source to change values

    @Mapping(target = "kitchen.id", source = "kitchenIdRefRequest.id")
    @Mapping(target = "address", source = "addressRequest", qualifiedByName = "toAddress")
    Restaurant fromRestaurantRequestToRestaurant(RestaurantRequest restaurantRequest);

    @Mapping(target = "kitchen.id", source = "kitchenIdRefRequest.id")
    @Mapping(target = "address.city.id", source = "addressRequest.cityIdRefRequest.id")
    void updateDomainObject(RestaurantRequest restaurantRequest, @MappingTarget Restaurant restaurant);
    //Declares a parameter of a mapping method to be the target of the mapping.
    //Not more than one parameter can be declared as MappingTarget.
}
