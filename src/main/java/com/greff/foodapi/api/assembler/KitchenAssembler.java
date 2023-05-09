package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.KitchenResponse;
import com.greff.foodapi.domain.mapper.KitchenMapper;
import com.greff.foodapi.domain.model.Kitchen;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class KitchenAssembler {

    private final KitchenMapper kitchenMapper;

    public KitchenResponse toModel(Kitchen kitchen) {
        return kitchenMapper.fromKitchenToKitchenResponse(kitchen);
    }

    public List<KitchenResponse> toCollectionModel(List<Kitchen> kitchens) {
        return kitchens.stream().map(this::toModel).toList();
    }
}
