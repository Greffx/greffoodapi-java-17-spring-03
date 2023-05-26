package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product, Long restaurantId);

    Product update(Product product, Long restaurantId);

    Product findProductThroughRestaurant(Long restaurantId, Long productId);

    List<Product> findAll(Long restaurantId);

    List<Product> findActiveProducts(Long restaurantId);
}