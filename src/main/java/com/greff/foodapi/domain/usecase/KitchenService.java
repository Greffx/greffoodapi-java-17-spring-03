package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Kitchen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KitchenService {

    Page<Kitchen> findAll(Pageable pageable);

    Kitchen findById(Long id);

    Boolean findByIfExistsName(String name);

    Kitchen create(Kitchen kitchen);

    Kitchen update(Kitchen kitchen);

    void deleteById(Long id);

    List<Kitchen> findByName(String name);
}
