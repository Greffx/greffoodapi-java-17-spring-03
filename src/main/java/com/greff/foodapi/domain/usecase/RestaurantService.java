package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantService {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, Long id);

    void patchFields(Map<String, Object> fields, Restaurant restaurant);
}
