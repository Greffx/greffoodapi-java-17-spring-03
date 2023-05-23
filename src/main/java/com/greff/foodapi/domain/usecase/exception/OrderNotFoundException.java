package com.greff.foodapi.domain.usecase.exception;

public class OrderNotFoundException extends NotFoundObjectException {

    public OrderNotFoundException(String resourceName, Long id) {
        super(resourceName, id);
    }
}
