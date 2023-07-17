package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.response.ProductPhotoResponse;
import com.greff.foodapi.domain.model.ProductPhoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPhotoMapper {

    ProductPhotoResponse fromProductPhotoToProductPhotoResponse(ProductPhoto productPhoto);
}
