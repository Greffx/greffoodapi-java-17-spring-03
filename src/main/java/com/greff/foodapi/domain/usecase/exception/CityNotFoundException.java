package com.greff.foodapi.domain.usecase.exception;

public class CityNotFoundException extends NotFoundObjectException {

    public CityNotFoundException(Long id) {
        super("City", id);
    }
}
