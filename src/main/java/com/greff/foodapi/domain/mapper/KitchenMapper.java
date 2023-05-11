package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.KitchenRequest;
import com.greff.foodapi.api.model.response.KitchenResponse;
import com.greff.foodapi.domain.model.Kitchen;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface KitchenMapper {

    KitchenResponse fromKitchenToKitchenResponse(Kitchen kitchen);

    Kitchen fromKitchenRequestToKitchen(KitchenRequest kitchenRequest);
}
