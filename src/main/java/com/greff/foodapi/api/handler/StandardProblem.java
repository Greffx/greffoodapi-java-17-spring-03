package com.greff.foodapi.api.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter //will have all methods getters for attributes, like getMessage
@Builder //project standard, can't be instanced like new ClassName(), needs to use another way to build object
//would be like StandardProblem.something().something().builder(), still will be a constructor
public class StandardProblem {

    private LocalDateTime dateTime;
    private String message;
}
