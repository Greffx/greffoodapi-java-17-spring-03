package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends CustomJpaRepository<Order, Long> {
}
