package com.greff.foodapi.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenRequest {

    @NotBlank
    private String name;
}
