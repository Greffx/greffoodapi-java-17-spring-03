package com.greff.foodapi.domain.usecase.exception;

public class CityNotFoundException extends NotFoundObjectException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long id) {
        this(String.format("City with id %d, not found", id)); //using this to not pass to parent constructor
    }
}
