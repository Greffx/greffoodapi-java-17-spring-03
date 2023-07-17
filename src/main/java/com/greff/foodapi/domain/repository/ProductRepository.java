package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Product;
import com.greff.foodapi.domain.model.ProductPhoto;
import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("SELECT p FROM Product p WHERE p.active = TRUE and p.restaurant = :restaurant")
    List<Product> findByActiveProducts(Restaurant restaurant);

    @Query("SELECT p FROM ProductPhoto p WHERE p.product.id = :productId AND p.product.restaurant.id = :restaurantId")
    Optional<ProductPhoto> findProductPhoto(Long productId, Long restaurantId);
}
