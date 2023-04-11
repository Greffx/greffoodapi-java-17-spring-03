package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, Long id);
}
