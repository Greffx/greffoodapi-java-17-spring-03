package com.greff.foodapi.domain.usecase.exception;

public class RestaurantNotFoundException extends NotFoundObjectException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long id) {
        this(String.format("Restaurant with id %d, not found", id)); //using this to not pass to parent constructor
    }
}
