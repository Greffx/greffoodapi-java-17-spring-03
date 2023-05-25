package com.greff.foodapi.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greff.foodapi.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponse {

        private String uuid; //exposing UUID to user, better than to expose real id, will use only internally, to queries and such
        //UUID is for users to use, to find by id and others
        private BigDecimal subtotal;
        private BigDecimal deliveryTax;
        private BigDecimal total;
        private OffsetDateTime creationDate;
        private OffsetDateTime confirmedDate;
        private OffsetDateTime canceledDate;
        private OffsetDateTime deliveredDate;
        private OrderStatus status;

        @JsonProperty("restaurant")
        private RestaurantResponse restaurantResponse;

        @JsonProperty("deliveryAddress")
        private AddressResponse addressResponse;

        @JsonProperty("user")
        private UserResponse userResponse;

        @JsonProperty("paymentMethod")
        private PaymentMethodResponse paymentMethodResponse;

        @JsonProperty("items")
        private List<OrderItemResponse> itemResponses;
}