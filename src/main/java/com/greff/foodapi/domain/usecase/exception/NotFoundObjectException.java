package com.greff.foodapi.domain.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundObjectException extends BusinessException {

    public NotFoundObjectException(String message) {
        super(message);
    }
}
