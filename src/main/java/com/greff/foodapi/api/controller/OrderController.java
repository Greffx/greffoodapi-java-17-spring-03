package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.OrderAssembler;
import com.greff.foodapi.api.assembler.OrderRequestDisassembler;
import com.greff.foodapi.api.model.request.OrderRequest;
import com.greff.foodapi.api.model.response.OrderResponse;
import com.greff.foodapi.api.model.response.SimpleOrderResponse;
import com.greff.foodapi.domain.usecase.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderAssembler orderAssembler;
    private final OrderService orderService;
    private OrderRequestDisassembler orderRequestDisassembler;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody @Valid OrderRequest orderRequest) {
        var order = orderRequestDisassembler.toDomainObject(orderRequest);

        order = orderService.create(order);

        return orderAssembler.toModel(order);
    }

    @PutMapping("/{id}/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteringOrderStatusToConfirmed(@PathVariable Long id) {
        var order = orderService.findById(id);

        orderService.alteringOrderStatusToConfirmed(order);
    }

    @PutMapping("/{id}/delivered")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteringOrderStatusToDelivered(@PathVariable Long id) {
        var order = orderService.findById(id);

        orderService.alteringOrderStatusToDelivered(order);
    }

    @PutMapping("/{id}/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteringOrderStatusToCanceled(@PathVariable Long id) {
        var order = orderService.findById(id);

        orderService.alteringOrderStatusToCanceled(order);
    }
}
