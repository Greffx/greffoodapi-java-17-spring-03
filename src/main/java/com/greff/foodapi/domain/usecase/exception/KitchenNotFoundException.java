package com.greff.foodapi.domain.usecase.exception;

public class KitchenNotFoundException extends NotFoundObjectException {

    public KitchenNotFoundException(Long id) {
        super("Kitchen", id);
    }
}
