package com.greff.foodapi.domain.usecase.exception;

public class InvalidUserPasswordException extends BusinessException {

    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
