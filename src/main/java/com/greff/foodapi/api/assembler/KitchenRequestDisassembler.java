package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.KitchenRequest;
import com.greff.foodapi.domain.mapper.KitchenMapper;
import com.greff.foodapi.domain.model.Kitchen;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KitchenRequestDisassembler {

    private final KitchenMapper kitchenMapper;

    public Kitchen toDomainObject(KitchenRequest kitchenRequest) {
        return kitchenMapper.fromKitchenRequestToKithcen(kitchenRequest);
    }
}
