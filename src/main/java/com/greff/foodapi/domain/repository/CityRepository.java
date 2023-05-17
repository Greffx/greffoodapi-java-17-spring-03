package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CustomJpaRepository<City, Long> {
}