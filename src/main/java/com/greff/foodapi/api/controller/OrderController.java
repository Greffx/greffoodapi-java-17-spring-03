package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.OrderAssembler;
import com.greff.foodapi.api.assembler.OrderRequestDisassembler;
import com.greff.foodapi.api.model.request.OrderRequest;
import com.greff.foodapi.api.model.response.OrderResponse;
import com.greff.foodapi.api.model.response.SimpleOrderResponse;
import com.greff.foodapi.domain.repository.filter.OrderFilter;
import com.greff.foodapi.domain.usecase.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public List<SimpleOrderResponse> findAll() {
        var orders = orderService.findAll();

        return orderAssembler.toSimpleCollectionModel(orders);
    }

    @GetMapping("/filter")
    public Page<SimpleOrderResponse> findAllWithFilters(OrderFilter orderFilter, Pageable pageable) {
        var ordersPage = orderService.findAllWithFilters(orderFilter, pageable);

        List<SimpleOrderResponse> ordersModel = orderAssembler.toSimpleCollectionModel(ordersPage.getContent());

        Page<SimpleOrderResponse> ordersModelPage = new PageImpl<>(ordersModel, pageable, ordersPage.getTotalElements());

        return ordersModelPage;
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
