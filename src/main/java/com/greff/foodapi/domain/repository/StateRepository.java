package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}