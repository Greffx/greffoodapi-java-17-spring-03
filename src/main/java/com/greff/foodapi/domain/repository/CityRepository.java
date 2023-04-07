package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}