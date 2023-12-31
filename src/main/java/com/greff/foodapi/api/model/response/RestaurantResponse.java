package com.greff.foodapi.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//EntityRequest and EntityResponse are specialized DTOs that are specific to client-server interactions.
//DTO is a data transfer object that represents an entity in a simplified format for transfer between different layers or services.
//It typically includes only the fields that are relevant for a particular operation or use case.
//Better to use than Original entity, safer and more practical
//this one is a representation model for API layer so model entity don't need to change because of that
//this one do everything already
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestaurantResponse {

    private Long id;
    private String name;
    private BigDecimal deliveryTax;
    private Boolean active;
    private Boolean open;

    @JsonProperty("kitchen")
    private KitchenResponse kitchenResponse;

    @JsonProperty("address")
    private AddressResponse addressResponse;
}
