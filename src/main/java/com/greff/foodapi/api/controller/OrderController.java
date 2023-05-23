package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.OrderAssembler;
import com.greff.foodapi.api.model.response.OrderResponse;
import com.greff.foodapi.api.model.response.SimpleOrderResponse;
import com.greff.foodapi.domain.usecase.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderAssembler orderAssembler;
    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        var order = orderService.findById(id);

        return orderAssembler.toModel(order);
    }

    @GetMapping
    public List<SimpleOrderResponse> findById() {
        var orders = orderService.findAll();

        return orderAssembler.toSimpleCollectionModel(orders);
    }

}
