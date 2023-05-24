package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.OrderRequest;
import com.greff.foodapi.api.model.response.OrderResponse;
import com.greff.foodapi.api.model.response.SimpleOrderResponse;
import com.greff.foodapi.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {RestaurantMapper.class, PaymentMethodMapper.class, UserMapper.class,
                AddressMapper.class, OrderItemMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "restaurantResponse.id", source = "restaurant.id")
    @Mapping(target = "restaurantResponse.name", source = "restaurant.name")
    @Mapping(target = "restaurantResponse.deliveryTax", ignore = true)
    @Mapping(target = "restaurantResponse.active", ignore = true)
    @Mapping(target = "restaurantResponse.open", ignore = true)
    @Mapping(target = "userResponse", source = "user", qualifiedByName = "toUserResponse")
    @Mapping(target = "paymentMethodResponse", source = "paymentMethod", qualifiedByName = "toPaymentMethodResponse")
    @Mapping(target = "addressResponse", source = "address", qualifiedByName = "toAddressResponse")
    @Mapping(target = "itemResponses", source = "order.items", qualifiedByName = "toOrderItemResponse")
    OrderResponse fromOrderToOrderResponse(Order order);

    @Mapping(target = "restaurantResponse.id", source = "restaurant.id")
    @Mapping(target = "restaurantResponse.name", source = "restaurant.name")
    @Mapping(target = "restaurantResponse.deliveryTax", ignore = true)
    @Mapping(target = "restaurantResponse.active", ignore = true)
    @Mapping(target = "restaurantResponse.open", ignore = true)
    @Mapping(target = "userResponse", source = "user", qualifiedByName = "toUserResponse")
    SimpleOrderResponse fromOrderToSimpleOrderResponse(Order order);

    @Mapping(target = "paymentMethod.id", source = "paymentMethodId.id")
    @Mapping(target = "restaurant.id", source = "restaurantId.id")
    @Mapping(target = "items", source = "itemRequests", qualifiedByName = "toOrderItem")
    @Mapping(target = "address", source = "addressRequest", qualifiedByName = "toAddress")
    Order fromOrderRequestToOrder(OrderRequest orderRequest);
}
