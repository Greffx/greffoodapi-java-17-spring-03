package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Order;

import java.util.List;

public interface OrderService {

    Order findById(Long id);

    List<Order> findAll();

    Order create(Order order);
}
