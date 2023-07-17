package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.ProductPhotoResponse;
import com.greff.foodapi.domain.mapper.ProductPhotoMapper;
import com.greff.foodapi.domain.model.ProductPhoto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductPhotoAssembler {

    private final ProductPhotoMapper mapper;

    public ProductPhotoResponse toModel(ProductPhoto productPhoto) {
        return mapper.fromProductPhotoToProductPhotoResponse(productPhoto);
    }

}
