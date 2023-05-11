package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.PaymentMethodRequest;
import com.greff.foodapi.api.model.response.PaymentMethodResponse;
import com.greff.foodapi.domain.model.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodResponse fromPaymentMethodToPaymentMethodResponse(PaymentMethod paymentMethod);

    @Mapping(target = "id", ignore = true)
    PaymentMethod fromPaymentMethodRequestToPaymentMethod(PaymentMethodRequest paymentMethodRequest);

    @Mapping(target = "id", ignore = true)
    PaymentMethod copyPaymentMethodToAnotherPaymentMethodInstance(PaymentMethod paymentMethodToCopy, @MappingTarget PaymentMethod paymentMethod);
}
