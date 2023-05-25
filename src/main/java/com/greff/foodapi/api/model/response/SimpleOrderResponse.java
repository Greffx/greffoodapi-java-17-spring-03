package com.greff.foodapi.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greff.foodapi.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class SimpleOrderResponse {

        private String uuid;
        private BigDecimal subtotal;
        private BigDecimal deliveryTax;
        private BigDecimal total;
        private OffsetDateTime creationDate;
        private OrderStatus status;

        @JsonProperty("restaurant")
        private RestaurantResponse restaurantResponse;

        @JsonProperty("user")
        private UserResponse userResponse;
}