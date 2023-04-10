package com.greff.foodapi.domain.usecase.exception;

public class NotFoundObjectException extends RuntimeException {
public NotFoundObjectException(String message) {
        super(message);
    }
}
