package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.ProductPhoto;

public interface ProductRepositoryQueries {

    ProductPhoto save(ProductPhoto productPhoto);

    void deletePhoto(ProductPhoto productPhoto);
}
