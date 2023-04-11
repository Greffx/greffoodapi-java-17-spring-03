package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.repository.RestaurantRepository;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundObjectException(String.format("Restaurant with id %d, not found", id)));
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }
}
