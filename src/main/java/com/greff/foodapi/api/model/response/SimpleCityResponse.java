package com.greff.foodapi.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleCityResponse {

    private Long id;
    private String name;
    private String state;
}