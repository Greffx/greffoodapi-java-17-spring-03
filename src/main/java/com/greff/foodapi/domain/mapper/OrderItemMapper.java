package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.OrderItemRequest;
import com.greff.foodapi.api.model.response.OrderItemResponse;
import com.greff.foodapi.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapper {

    @Named("toOrderItemResponse")
    @Mapping(target = "productId", source = "orderItem.product.id")
    @Mapping(target = "productName", source = "orderItem.product.name")
    OrderItemResponse fromOrderItemToOrderItemResponse(OrderItem orderItem);

    @Named("toOrderItem")
    @Mapping(target = "product.id", source = "orderItemRequest.productId")
    OrderItem fromOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest);

}
