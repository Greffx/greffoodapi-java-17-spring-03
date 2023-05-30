package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.PaymentMethodAssembler;
import com.greff.foodapi.api.assembler.PaymentMethodRequestDisassembler;
import com.greff.foodapi.api.model.request.PaymentMethodRequest;
import com.greff.foodapi.api.model.response.PaymentMethodResponse;
import com.greff.foodapi.domain.usecase.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payment_methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;
    private final PaymentMethodAssembler paymentMethodAssembler;
    private final PaymentMethodRequestDisassembler paymentMethodRequestDisassembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodResponse create(@RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
        var paymentMethod = paymentMethodRequestDisassembler.toDomainObject(paymentMethodRequest);

        paymentMethod = paymentMethodService.create(paymentMethod);

        return paymentMethodAssembler.toModel(paymentMethod);
    }

    @PutMapping("/{id}")
    public PaymentMethodResponse update(@RequestBody @Valid PaymentMethodRequest paymentMethodRequest, @PathVariable Long id) {
        var paymentMethod = paymentMethodService.findById(id);

        paymentMethodRequestDisassembler.updatePaymentMethodDomainObject(paymentMethodRequest, paymentMethod);
        paymentMethodService.update(paymentMethod);

        return paymentMethodAssembler.toModel(paymentMethod);
    }

    @GetMapping("/{id}")
    public PaymentMethodResponse findById(@PathVariable Long id) {
        var paymentMethod = paymentMethodService.findById(id);

        return paymentMethodAssembler.toModel(paymentMethod);
    }

    @GetMapping
    public List<PaymentMethodResponse> findAll() {
        var paymentMethods = paymentMethodService.findAll();

        return paymentMethodAssembler.toCollectionModel(paymentMethods);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        paymentMethodService.deleteById(id);
    }
}
