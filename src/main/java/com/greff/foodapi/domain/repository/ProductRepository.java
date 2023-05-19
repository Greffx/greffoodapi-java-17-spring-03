package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long> {
}
