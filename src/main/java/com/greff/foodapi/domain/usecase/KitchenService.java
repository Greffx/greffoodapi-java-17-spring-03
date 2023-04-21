package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Kitchen;

import java.util.List;

public interface KitchenService {

    List<Kitchen> findAll();

    Kitchen findById(Long id);

    Boolean findByIfExistsName(String name);

    Kitchen create(Kitchen kitchen);

    Kitchen update(Kitchen kitchen, Long id);

    void deleteById(Long id);

    List<Kitchen> findByName(String name);
}
