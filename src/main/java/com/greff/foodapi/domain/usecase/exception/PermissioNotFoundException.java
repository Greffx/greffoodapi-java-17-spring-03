package com.greff.foodapi.domain.usecase.exception;

public class PermissioNotFoundException extends NotFoundObjectException {

    public PermissioNotFoundException(String resourceName, Long id, String resourceName2, Long id2) {
        super(resourceName, id, resourceName2, id2);
    }
}
