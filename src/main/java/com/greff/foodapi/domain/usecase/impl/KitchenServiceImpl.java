package com.greff.foodapi.domain.usecase.impl;
//service domain is without state and do tasks to domain, like behavior of application, business rules and such
//can't have contact with http protocol or such, not cool to use responseStatus in here

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.repository.KitchenRepository;
import com.greff.foodapi.domain.usecase.KitchenService;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //it's a @component, to define Domain-Driven Design, DDD. To id better, to see that is a service class
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
        return kitchenRepository.findById(id).orElseThrow(() -> //this orElse means like 'if it's empty inside, throw this exception, with lambda and pass only constructor of exception
                new NotFoundObjectException(String.format("This id %d, was not found", id)));
    }

    @Override
    public Boolean findByIfExistsName(String name) { //query which returns boolean, can be used for business rules or something that needs validation, very util
        return kitchenRepository.existsByName(name);
    }

    @Override
    public Kitchen addKitchen(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    public Kitchen updateKitchen(Kitchen kitchen, Long id) {
        Kitchen kitchenToChange = findById(id);
        kitchenToChange.setName(kitchen.getName());
        return addKitchen(kitchenToChange);

    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        kitchenRepository.deleteById(id);
    }

    @Override
    public List<Kitchen> findByName(String name) {
        return kitchenRepository.findByName(name);
    }
}
