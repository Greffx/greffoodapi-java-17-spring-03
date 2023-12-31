package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.KitchenRequest;
import com.greff.foodapi.api.model.response.KitchenResponse;
import com.greff.foodapi.domain.model.Kitchen;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KitchenMapper {

    @Named("toKitchenResponse")
    KitchenResponse fromKitchenToKitchenResponse(Kitchen kitchen);

    Kitchen fromKitchenRequestToKitchen(KitchenRequest kitchenRequest);

    void updateDomainObject(KitchenRequest kitchenRequest, @MappingTarget Kitchen kitchen);
}
