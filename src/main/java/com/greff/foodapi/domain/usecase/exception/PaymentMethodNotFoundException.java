package com.greff.foodapi.domain.usecase.exception;

public class PaymentMethodNotFoundException extends NotFoundObjectException {

    public PaymentMethodNotFoundException(Long id) {
        super("Payment method", id);
    }
}
