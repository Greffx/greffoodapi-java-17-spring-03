package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Order;
import com.greff.foodapi.domain.repository.filter.OrderFilter;

import java.util.List;

public interface OrderService {

    Order findByUuid(String uuid);

    List<Order> findAll();

    List<Order> findAllWithFilters(OrderFilter orderFilter);

    Order create(Order order);

    void alteringOrderStatusToConfirmed(Order order);

    void alteringOrderStatusToDelivered(Order order);

    void alteringOrderStatusToCanceled(Order order);
}
