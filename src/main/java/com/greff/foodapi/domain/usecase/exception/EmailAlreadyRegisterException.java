package com.greff.foodapi.domain.usecase.exception;

public class EmailAlreadyRegisterException extends BusinessException {

    public EmailAlreadyRegisterException(String message) {
        super(message);
    }
}
