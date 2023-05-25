package com.greff.foodapi.domain.usecase.exception;

public class OrderNotFoundException extends NotFoundObjectException {

    public OrderNotFoundException(String resourceName, String id) {
        super(resourceName, id);
    }
}
