package com.greff.foodapi.domain.usecase.exception;

public class RestaurantNotFoundException extends NotFoundObjectException {

    public RestaurantNotFoundException(Long id) {
        super("Restaurant", id);
    }

    public RestaurantNotFoundException(String name) {
        super(String.format("Restaurant with name %s, not found", name));
    }
}
