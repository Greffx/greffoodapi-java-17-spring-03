package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.PaymentMethodRequest;
import com.greff.foodapi.domain.mapper.PaymentMethodMapper;
import com.greff.foodapi.domain.model.PaymentMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentMethodRequestDisassembler {

    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethod toDomainObject(PaymentMethodRequest paymentMethodRequest) {
        return paymentMethodMapper.fromPaymentMethodRequestToPaymentMethod(paymentMethodRequest);
    }
}
