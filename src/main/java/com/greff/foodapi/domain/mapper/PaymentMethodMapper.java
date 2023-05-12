package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.PaymentMethodRequest;
import com.greff.foodapi.api.model.response.PaymentMethodResponse;
import com.greff.foodapi.domain.model.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodMapper {

    PaymentMethodResponse fromPaymentMethodToPaymentMethodResponse(PaymentMethod paymentMethod);

    PaymentMethod fromPaymentMethodRequestToPaymentMethod(PaymentMethodRequest paymentMethodRequest);

    void updateDomainObject(PaymentMethodRequest paymentMethodRequest, @MappingTarget PaymentMethod paymentMethod);
}
