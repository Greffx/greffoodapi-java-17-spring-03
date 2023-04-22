package com.greff.foodapi.domain.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class NotFoundObjectException extends BusinessException {
    //we can not instance more this type of exception, serves to help others
    //but can be used in catch, so if needed to catch something more general, can use this

    //Abstract classes should not have public constructors,
    // Constructors of abstract classes can only be called in constructors of their subclasses
    protected NotFoundObjectException(String message) {
        super(message);
    }
}
