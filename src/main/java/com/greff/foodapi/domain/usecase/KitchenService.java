package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Kitchen;

import java.util.List;

public interface KitchenService {

    List<Kitchen> findAll();

    Kitchen findById(Long id);
}
