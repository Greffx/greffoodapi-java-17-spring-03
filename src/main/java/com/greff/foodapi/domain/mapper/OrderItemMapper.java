package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.response.OrderItemResponse;
import com.greff.foodapi.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Named("toOrderItemResponse")
    @Mapping(target = "productId", source = "orderItem.product.id")
    @Mapping(target = "productName", source = "orderItem.product.name")
    OrderItemResponse fromOrderItemToOrderItemResponse(OrderItem orderItem);
}
