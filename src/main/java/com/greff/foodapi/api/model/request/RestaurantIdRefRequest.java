package com.greff.foodapi.api.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantIdRefRequest {

    @NotNull
    @Min(1)
    private Long id;
}
