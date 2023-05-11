package com.greff.foodapi.api.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityIdRefRequest {

    @NotNull
    private Long id;
}