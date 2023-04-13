package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RestaurantService {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    List<Restaurant> findByDeliveryTax(BigDecimal lower, BigDecimal higher);

    List<Restaurant> findByNameAndKitchen(String name, Long kitchenId);

    List<Restaurant> findTwoRestaurantsByName(String name);

    Restaurant findFirstOneByName(String name);

    Integer findHowManyRestaurantsPerKitchen(Long kitchenId);

    List<Restaurant> findWithFreeDeliveryTaxAndWithSimilarName(String name);

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, Long id);

    void patchFields(Map<String, Object> fields, Restaurant restaurant);
}
