package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City findById(Long id);

    City create(City city);

    City update(City city);

    void delete(Long id);
}
