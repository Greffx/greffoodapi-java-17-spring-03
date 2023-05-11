package com.greff.foodapi.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    @NotBlank
    private String cep;

    @NotBlank
    private String publicArea;

    @NotBlank
    private String number;

    @NotBlank
    private String complement;

    @NotBlank
    private String street;

    @JsonProperty("city")
    @NotNull
    @Valid
    private CityIdRefRequest cityIdRefRequest;
}