package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.PaymentMethod;

import java.util.Set;

public interface PaymentMethodService {

    PaymentMethod create(PaymentMethod paymentMethod);

    PaymentMethod update(PaymentMethod paymentMethod);

    PaymentMethod findById(Long id);

    Set<PaymentMethod> findAll();

    void deleteById(Long id);

}
