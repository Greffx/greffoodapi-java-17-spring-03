package com.greff.foodapi.domain.usecase.exception;

public class UserNotFoundException extends NotFoundObjectException {

    public UserNotFoundException(String resourceName, Long id) {
        super(resourceName, id);
    }
}
