package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Restaurant;

import java.util.List;

//interface where is created our customized methods that will be implemented in RestaurantRepositoryImpl
public interface RestaurantRepositoryCustomizedQueries {

    List<Restaurant> findWithFreeTaxDelivery(String name);
    //will return a list of restaurants with free tax delivery with similar name to param 'name'
}
