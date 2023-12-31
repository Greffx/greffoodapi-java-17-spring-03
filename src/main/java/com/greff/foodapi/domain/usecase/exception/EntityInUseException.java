package com.greff.foodapi.domain.usecase.exception;

public class EntityInUseException extends BusinessException {

    public EntityInUseException(String message) {
        super(message);
    }

    public EntityInUseException(String entityName, Long id) {
        this(String.format("%s with id %d can't be removed because still been used", entityName, id));
    }
}
