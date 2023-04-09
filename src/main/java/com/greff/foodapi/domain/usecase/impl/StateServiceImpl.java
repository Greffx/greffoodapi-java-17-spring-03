package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.repository.StateRepository;
import com.greff.foodapi.domain.usecase.StateService;
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
}
