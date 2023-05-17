package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.PaymentMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends CustomJpaRepository<PaymentMethod, Long> {
}