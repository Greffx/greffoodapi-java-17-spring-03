package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    //will query restaurants like DeliveryTax between lowerT >= and biggestT <=
    //method name is not ok to use it, that expose a lot of attributes and such, is not conventional
    List<Restaurant> findByDeliveryTaxGreaterThanEqualAndDeliveryTaxLessThanEqual(BigDecimal lowerTax, BigDecimal biggestTax);

    //find by name like 'something' and kitchen id
    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);
}