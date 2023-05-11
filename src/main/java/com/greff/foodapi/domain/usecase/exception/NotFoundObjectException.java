package com.greff.foodapi.domain.usecase.exception;

public abstract class NotFoundObjectException extends BusinessException {
    //we can not instance more this type of exception, serves to help others
    //but can be used in catch, so if needed to catch something more general, can use this

    public NotFoundObjectException(String message) {
        super(message);
    }

    //Abstract classes should not have public constructors,
    // Constructors of abstract classes can only be called in constructors of their subclasses
    protected NotFoundObjectException(String resourceName, Long id) {
        super(String.format("%s with id %d, not found", resourceName, id));
    }
}
