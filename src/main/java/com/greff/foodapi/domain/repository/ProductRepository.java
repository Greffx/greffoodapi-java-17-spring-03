package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Product;
import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.active = TRUE and p.restaurant = :restaurant")
    List<Product> findByActiveProducts(Restaurant restaurant);
}
