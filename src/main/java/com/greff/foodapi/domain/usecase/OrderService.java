package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Order;
import com.greff.foodapi.domain.model.filter.OrderFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order findByUuid(String uuid);

    List<Order> findAll();

    Page<Order> findAllWithFilters(OrderFilter orderFilter, Pageable pageable);

    Order create(Order order);

    void alteringOrderStatusToConfirmed(Order order);

    void alteringOrderStatusToDelivered(Order order);

    void alteringOrderStatusToCanceled(Order order);
}
