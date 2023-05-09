package com.greff.foodapi.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StateRequest {

    @NotBlank
    private String name;
}
