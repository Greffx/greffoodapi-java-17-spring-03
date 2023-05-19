package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Product;
import com.greff.foodapi.domain.repository.ProductRepository;
import com.greff.foodapi.domain.usecase.ProductService;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final RestaurantService restaurantService;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Product create(Product product, Long restaurantId) {
        var restaurant = restaurantService.findById(restaurantId);

        product.setRestaurant(restaurant);

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product update(Product product, Long restaurantId) {
        return create(product, restaurantId);
    }

    @Override
    public Product findProductThroughRestaurant(Long restaurantId, Long productId) {
        var restaurant = restaurantService.findById(restaurantId);

        return restaurant.getProducts().stream()
                .filter(productToCompare -> productToCompare.getId().equals(productId)).findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product", productId, "Restaurant", restaurantId));
    }

    @Override
    public List<Product> findAll(Long restaurantId) {
        var restaurant = restaurantService.findById(restaurantId);

        return restaurant.getProducts();
    }
}