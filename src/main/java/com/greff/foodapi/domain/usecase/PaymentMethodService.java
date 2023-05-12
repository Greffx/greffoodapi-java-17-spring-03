package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

    PaymentMethod create(PaymentMethod paymentMethod);

    PaymentMethod update(PaymentMethod paymentMethod);

    PaymentMethod findById(Long id);

    List<PaymentMethod> findAll();

    void deleteById(Long id);

}
