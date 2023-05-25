package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Order;

import java.util.List;

public interface OrderService {

    Order findByUuid(String uuid);

    List<Order> findAll();

    Order create(Order order);

    void alteringOrderStatusToConfirmed(Order order);

    void alteringOrderStatusToDelivered(Order order);

    void alteringOrderStatusToCanceled(Order order);
}
