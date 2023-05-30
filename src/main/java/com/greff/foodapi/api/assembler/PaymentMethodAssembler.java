package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.PaymentMethodResponse;
import com.greff.foodapi.domain.mapper.PaymentMethodMapper;
import com.greff.foodapi.domain.model.PaymentMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Component
public class PaymentMethodAssembler {

    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethodResponse toModel(PaymentMethod paymentMethod) {
        return paymentMethodMapper.fromPaymentMethodToPaymentMethodResponse(paymentMethod);
    }

    public List<PaymentMethodResponse> toCollectionModel(Collection<PaymentMethod> paymentMethods) {
        return paymentMethods.stream().map(this::toModel).toList();
    }
}
