package com.greff.foodapi.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantRequest {

    //validation rule, restriction, this annotation says that doesn't accept null values, instead of database validate, our application does it
    //validation happen in JPA repository, so don't try to do insert, will stop early at preInsert
    //@NotEmpty means that don't accept null or ""(means empty)
    @NotBlank //verifies not null, not empty "" and can't be only whitespace without nothing, like "   "
    private String name;

    //@DecimalMin("1") minimum string value, can see in class why, because bigDecimal representation is string
    @PositiveOrZero //another annotation that say the same thing
    //don't change the name of attribute. But the representation in JSON will be changed
    @JsonProperty("delivery_tax")
    private BigDecimal deliveryTax;

    @NotNull
    //this one is saying that an instance of kitchen is need it, but need to validate its properties to,
    //because this one only check instance of object's there
    @Valid
    //using valid in Kitchen type says that don't want only to check an instance of kitchen is null, but its properties too, can't be null either
    //this is a cascade validation type
    @JsonProperty("kitchen")
    private KitchenIdRefRequest kitchenIdRefRequest;
}
