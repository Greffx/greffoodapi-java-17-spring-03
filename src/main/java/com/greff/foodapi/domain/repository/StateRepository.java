package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.State;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CustomJpaRepository<State, Long> {
}