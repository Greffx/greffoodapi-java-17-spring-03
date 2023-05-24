package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.OrderRequest;
import com.greff.foodapi.domain.mapper.OrderMapper;
import com.greff.foodapi.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderRequestDisassembler {

    private final OrderMapper orderMapper;

    public Order toDomainObject(OrderRequest orderRequest) {
        return orderMapper.fromOrderRequestToOrder(orderRequest);
    }
}
