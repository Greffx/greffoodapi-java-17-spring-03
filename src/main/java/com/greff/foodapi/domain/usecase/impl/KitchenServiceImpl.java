package com.greff.foodapi.domain.usecase.impl;
//service domain is without state and do tasks to domain, like behavior of application, business rules and such
//can't have contact with http protocol or such, not cool to use responseStatus in here

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.repository.KitchenRepository;
import com.greff.foodapi.domain.usecase.KitchenService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.KitchenNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //it's a @component, to define Domain-Driven Design, DDD. To id better, to see that is a service class
public class KitchenServiceImpl implements KitchenService {

    private final KitchenRepository kitchenRepository; //injection of repository, to get crud methods of this

    public KitchenServiceImpl(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public Page<Kitchen> findAll(Pageable pageable) {
        //the return will be a list of kitchens that we got with repository
        //this method findAll from JPA repository, accepts PAGEABLE
        //and returns a PAGE now, not a list anymore
        return kitchenRepository.findAll(pageable);
    }

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id).orElseThrow(() ->
                new KitchenNotFoundException(id));
        //this orElse means like 'if it's empty inside, throw this exception, with lambda and pass only constructor of exception
        //it's like a little rule, search and find or throw exception, it good to be in service layer
    }

    @Override
    public Boolean findByIfExistsName(String name) { //query which returns boolean, can be used for business rules or something that needs validation, very util
        return kitchenRepository.existsByName(name);
    }

    @Transactional
    //good practice, because if had some rule or logic about removing or creating with another repository, could have problems in the future
    //because it could open two commits, because save, update, delete, methods that alter data function with transactional commits
    //if this one had remove kitchen then save kitchen, probably would have opened 2 commits and will not work right
    //that's why, in resume, I used this annotation @Transactional
    @Override
    public Kitchen create(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    @Override
    public Kitchen update(Kitchen kitchen) {
        return create(kitchen);
    }

    @Transactional //spring that manager this transaction, that annotation indicate that
    @Override
    public void deleteById(Long id) {
        findById(id);

        try {
            kitchenRepository.deleteById(id); //when make update, insert, delete alteration, JPA entity manager don't do it right all
            //will go in a queue ('fila de espera'), when a function that alters database is called, JPA entity manager wait to do all at the same time
            kitchenRepository.flush(); //Flushes all pending changes to the database.
            //flush method forces JPA to discharge NOW all requests in queue, like operations on hold
            //like execute operation on database, like delete by id, BUT THIS IS NOT a commit yet

            //so, flushes forces JPA entity manager to make that function first
            //fazemos isso pq quando a anotação @Transactional esta no método, e a funcionalidade do JPA entity manager é para fazer
            //as ações de métodos q alteram o banco como salvar, deletar ou atualizar depois, ele não faz logo de primeira, vai parar em uma espécie de fila
            //ele irá sair desse bloco try catch e ir para a realização do commit e assim o JPA irá fazer as suas ações caso tenha exception,
            //então flush método é para ele relizar primeiro as funções em Banco de dados e assim o bloco try catch pega

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("Kitchen", id);
        }
    }

    @Override
    public List<Kitchen> findByName(String name) {
        return kitchenRepository.findByName(name);
    }
}
