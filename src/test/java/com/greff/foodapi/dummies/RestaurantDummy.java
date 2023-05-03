package com.greff.foodapi.dummies;

import com.greff.foodapi.domain.model.Restaurant;

import java.math.BigDecimal;

public class RestaurantDummy {

    public static Restaurant instanceOfRestaurantDummy() {
        Restaurant restaurant = new Restaurant();

        restaurant.setId(1L);
        restaurant.setName("Ganesh_Fast_Food");
        restaurant.setDeliveryTax(BigDecimal.valueOf(3.50));
        restaurant.setKitchen(KitchenDummy.instanceOfKitchenDummy());

        return restaurant;
    }

}
