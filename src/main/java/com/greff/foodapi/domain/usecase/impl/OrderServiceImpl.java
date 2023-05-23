package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Order;
import com.greff.foodapi.domain.repository.OrderRepository;
import com.greff.foodapi.domain.usecase.OrderService;
import com.greff.foodapi.domain.usecase.exception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order", id));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
