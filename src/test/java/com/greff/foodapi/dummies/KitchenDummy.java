package com.greff.foodapi.dummies;

import com.greff.foodapi.domain.model.Kitchen;

public class KitchenDummy { //helps to make tests easiest to read

    public static Kitchen instanceOfKitchenDummy() {
        Kitchen kitchen = new Kitchen();

        kitchen.setId(1L);
        kitchen.setName("Indian");

        return kitchen;
    }
}
