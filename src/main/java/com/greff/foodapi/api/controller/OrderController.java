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

    @GetMapping("/{uuid}")
    public OrderResponse findById(@PathVariable String uuid) {
        var order = orderService.findByUuid(uuid);

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

    @PutMapping("/{uuid}/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteringOrderStatusToConfirmed(@PathVariable String uuid) {
        var order = orderService.findByUuid(uuid);

        orderService.alteringOrderStatusToConfirmed(order);
    }

    @PutMapping("/{uuid}/delivered")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteringOrderStatusToDelivered(@PathVariable String uuid) {
        var order = orderService.findByUuid(uuid);

        orderService.alteringOrderStatusToDelivered(order);
    }

    @PutMapping("/{uuid}/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteringOrderStatusToCanceled(@PathVariable String uuid) {
        var order = orderService.findByUuid(uuid);

        orderService.alteringOrderStatusToCanceled(order);
    }
}
