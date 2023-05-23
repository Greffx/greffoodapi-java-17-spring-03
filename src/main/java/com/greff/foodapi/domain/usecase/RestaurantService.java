package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Restaurant;
import jakarta.servlet.http.HttpServletRequest;

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

    void activation(Long id);

    void deactivation(Long id);

    Restaurant update(Restaurant restaurant);

    void patchFields(Map<String, Object> fields, Restaurant restaurant, HttpServletRequest request);

    void deleteById(Long id);

    void restaurantDissociationWithPaymentMethod(Long restaurantId, Long paymentMethodId);

    void restaurantAssociationWithPaymentMethod(Long restaurantId, Long paymentMethodId);

    void closingRestaurant(Long id);

    void openingRestaurant(Long id);

    void disassociateResponsible(Long restaurantId, Long responsbileId);

    void associateResponsible(Long restaurantId, Long responsbileId);

}