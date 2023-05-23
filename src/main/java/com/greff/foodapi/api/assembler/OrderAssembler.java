package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.OrderResponse;
import com.greff.foodapi.api.model.response.SimpleOrderResponse;
import com.greff.foodapi.domain.mapper.OrderMapper;
import com.greff.foodapi.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class OrderAssembler {

    private final OrderMapper orderMapper;

    public OrderResponse toModel(Order order) {
        return orderMapper.fromOrderToOrderResponse(order);
    }

    public List<OrderResponse> toCollectionModel(List<Order> orders) {
        return orders.stream().map(this::toModel).toList();
    }

    public List<SimpleOrderResponse> toSimpleCollectionModel(List<Order> orders) {
        return orders.stream().map(orderMapper::fromOrderToSimpleOrderResponse).toList();
    }
}
