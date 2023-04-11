package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.repository.KitchenRepository;
import com.greff.foodapi.domain.usecase.KitchenService;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {

    private final KitchenRepository kitchenRepository; //injection of repository, to get crud methods of this

    public KitchenServiceImpl(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public List<Kitchen> findAll() {
        return kitchenRepository.findAll(); //the return will be a list of kitchens that we got with repository
    }

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("This object was not found"));
    }

    @Override
    public Kitchen addKitchen(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    public Kitchen updateKitchen(Kitchen kitchen, Long id) {
        Kitchen kitchen1 = findById(id);
        return kitchenRepository.save(updateMethod(kitchen1, kitchen));

    }

    private Kitchen updateMethod(Kitchen kitchenToBeChange, Kitchen kitchen) {
        kitchenToBeChange.setName(kitchen.getName());
        return kitchenToBeChange;
    }
}
