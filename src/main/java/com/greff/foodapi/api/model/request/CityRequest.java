package com.greff.foodapi.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityRequest {

    @NotBlank
    private String name;

    @NotNull
    @Valid
    //@ConvertGroup(to = Groups.StateId.class)
    @JsonProperty("state")
    private StateIdRefRequest stateIdRefRequest;
}
