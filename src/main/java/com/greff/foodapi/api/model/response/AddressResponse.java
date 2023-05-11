package com.greff.foodapi.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    private String cep;
    private String publicArea;
    private String number;
    private String complement;
    private String street;

    @JsonProperty("city")
    private SimpleCityResponse simpleCityResponse;
}
