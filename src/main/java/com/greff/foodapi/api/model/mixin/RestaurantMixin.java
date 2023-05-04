package com.greff.foodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.greff.foodapi.domain.model.Address;
import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.model.PaymentMethod;
import com.greff.foodapi.domain.model.Product;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMixin {
    //JSON Ignore is from jackson 'import com.fasterxml.jackson.annotation.JsonIgnore', as you see
    //so that jackson is what does the serialization JSON to java object  and deserialization object java to JSON
    //there's no such greater connection with model layer, but more in common with API layer
    //because its modifying resources (like entities) representation
    //that's why this MIX-IN class, which got properties, attributes of original entity class
    //can be added many things here, like annotations, methods, attributes and more
    //going into api package because it's more related

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "name"} )
    //this attribute 'hibernateLazyInitializer' need to be serialized, so we ignore this property and shall not throw another exception about proxy again
    //allowGetters = true is allowing to when is just requesting to get, it's ok to be serialized, but it's bugging with 'hibernateLazyInitializer' so I took out
    private Kitchen kitchen;

    @JsonIgnore
    private Address address;

    private OffsetDateTime creationDate;

    private OffsetDateTime updateDate;

    @JsonIgnore
    private List<Product> product = new ArrayList<>();

    @JsonIgnore
    private List<PaymentMethod> paymentMethods;
}
