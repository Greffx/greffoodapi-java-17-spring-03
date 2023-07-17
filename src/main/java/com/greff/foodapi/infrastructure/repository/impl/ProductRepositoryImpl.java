package com.greff.foodapi.infrastructure.repository.impl;

import com.greff.foodapi.domain.model.ProductPhoto;
import com.greff.foodapi.domain.repository.ProductRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override //should put transacional on both methods, but I won't
    public ProductPhoto save(ProductPhoto productPhoto) {
        return manager.merge(productPhoto);
    }

    @Override
    public void deletePhoto(ProductPhoto productPhoto) {
        manager.remove(productPhoto);
    }
}
