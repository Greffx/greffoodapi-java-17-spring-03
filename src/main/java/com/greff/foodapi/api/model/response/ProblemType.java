package com.greff.foodapi.api.model.response;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"), //constant to be used in problemDetails
    //that way we don't need to do it in customHandler, if there's more than 100 methods, we would need to write each one, that way, only use constant enum
    ENTITY_IN_USE("/entity-in-use", "Entity is been used"),
    INVALID_DATA("/invalid-data", "Invalid data"),
    INVALID_MESSAGE("/invalid-message", "Invalid message"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    INTERNAL_ERROR("/internal-error", "Internal error");

    private final String title; //type of problem title
    private final String uri; //type of problem uri

    ProblemType(String path, String title) {
        this.uri = "https://greffood.com.br" + path;
        this.title = title;
    }
}
