package com.greff.foodapi.api.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenIdRefRequest {

    @NotNull
    private Long id;
}
