package com.greff.foodapi.domain.usecase.exception;

//general error for business, can be used in anytype of error of business
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) { //Throwable first of all exceptions, big parent one here
        super(message, cause);
    }
}
