package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    //will query restaurants like DeliveryTax between lowerT >= and biggestT <=
    //method name is not ok to use it, that expose a lot of attributes and such, is not conventional
    //others prefixes can be used, as 'READ', 'GET', 'QUERY' or 'STREAM', they all do the same thing as find
    List<Restaurant> queryByDeliveryTaxGreaterThanEqualAndDeliveryTaxLessThanEqual(BigDecimal lowerTax, BigDecimal biggestTax);

    //find by name like 'something' and kitchen id
    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);

    //will get the first restaurant that get name like string 'name', making 'LIKE' and limiting at same time
    Optional<Restaurant> getFirstRestaurantByNameContaining(String name);

    //query of 2 first with name like string 'name'
    List<Restaurant> streamTop2ByNameContaining(String name);

    //query how many restaurants is there with kitchen id
    Integer countByKitchenId(Long kitchenId);

    //can be returned optionals, lists, hashmaps, others collections, but needs to make sense
}