package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.repository.StateRepository;
import com.greff.foodapi.domain.usecase.StateService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
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
                new NotFoundObjectException(String.format("State with id %d, not found", id)));
    }

    @Override
    public State create(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State update(State state, Long id) {
        State stateToChange = findById(id);
        stateToChange.setName(state.getName());
        return create(stateToChange);
    }

    @Override
    public void delete(Long id) {
        try {
            if (findById(id) != null) stateRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("State id %d can't be deleted, it's attached to a city", id));
        }
    }
}
