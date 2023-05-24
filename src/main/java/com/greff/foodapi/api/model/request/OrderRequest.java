package com.greff.foodapi.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    @Valid
    @JsonProperty("restaurant")
    private RestaurantIdRefRequest restaurantId;

    @NotNull
    @Valid
    @JsonProperty("paymentMethod")
    private PaymentMethodIdRefRequest paymentMethodId;

    @NotNull
    @Valid
    @JsonProperty("deliveryAddress")
    private AddressRequest addressRequest;

    @NotNull
    @Valid
    @JsonProperty("items")
    private List<OrderItemRequest> itemRequests;
}
