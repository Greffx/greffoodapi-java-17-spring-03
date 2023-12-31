package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.repository.StateRepository;
import com.greff.foodapi.domain.usecase.StateService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.StateNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public State findById(Long id) {
        return stateRepository.findById(id).orElseThrow(() ->
                new StateNotFoundException(id));
    }

    @Override
    public State create(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State update(State state) {
        return create(state);
    }

    @Override
    public void delete(Long id) {
        findById(id);

        try {
            stateRepository.deleteById(id);
            stateRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("State", id);
        }
    }
}
