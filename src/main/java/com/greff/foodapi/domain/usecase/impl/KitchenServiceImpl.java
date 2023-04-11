package com.greff.foodapi.domain.usecase.impl; //service domain is without state and do tasks to domain, like behavior of application, business rules and such

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.repository.KitchenRepository;
import com.greff.foodapi.domain.usecase.KitchenService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.dao.DataIntegrityViolationException;
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
        return kitchenRepository.findById(id).orElseThrow(() ->
                new NotFoundObjectException("This object was not found"));
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
        try {
            findById(id);
            kitchenRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) { //structure exception, can be treated in here, this exception happens when entity object is inserted in another objects entities
            throw new EntityInUseException("Can't remove a kitchen with data attached to it"); //customized exception to treat some specific problems
        }
    }
}
