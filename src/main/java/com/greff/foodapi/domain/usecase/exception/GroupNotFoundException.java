package com.greff.foodapi.domain.usecase.exception;

public class GroupNotFoundException extends NotFoundObjectException {

    public GroupNotFoundException(String resourceName, Long id) {
        super(resourceName, id);
    }
}
