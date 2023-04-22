package com.greff.foodapi.domain.usecase.exception;

public class KitchenNotFoundException extends NotFoundObjectException {

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long id) {
        this(String.format("Kitchen with id %d, not found", id)); //using this to not pass to parent constructor
    }
}
