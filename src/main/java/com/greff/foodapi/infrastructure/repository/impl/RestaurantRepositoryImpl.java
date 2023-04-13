package com.greff.foodapi.infrastructure.repository.impl;

import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.repository.RestaurantRepository;
import com.greff.foodapi.domain.repository.RestaurantRepositoryCustomizedQueries;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.greff.foodapi.infrastructure.repository.spec.RestaurantSpecs.withFreeDeliveryTax;
import static com.greff.foodapi.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;

@Repository //bean to validate as a repository too
//class will inherit this interface and impl methods in there
public class RestaurantRepositoryImpl /*z*/ implements RestaurantRepositoryCustomizedQueries /*y*/ {

    //circular references is discouraged, they are prohibited by default. Happens when ex: x is injected z and x is inherited y
    //so when containers spring are instancing in runtime will happen:
    //RestaurantRepositoryImpl will be created and injected RestaurantRepository dependencies
    //and RestaurantRepository will be created and when inheriting RestaurantRepositoryCustomizedQueries will inject RestaurantRepositoryImpl dependencies too,
    // that's wil circular, both of them will be injected in each other

    private final RestaurantRepository restaurantRepository;/*x*/

    @Lazy //means that will instance dependency only when required, when method is called
    public RestaurantRepositoryImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findWithFreeTaxDelivery(String name) {
        //'AND' is a specification too, to bind specifications
        return restaurantRepository.findAll(withFreeDeliveryTax().and(withSimilarName(name)));
    }
}
