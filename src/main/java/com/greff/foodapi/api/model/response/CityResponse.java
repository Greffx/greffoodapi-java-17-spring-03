package com.greff.foodapi.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {

    private Long id;
    private String name;

    @JsonProperty("state")
    private StateResponse stateResponse;
}
