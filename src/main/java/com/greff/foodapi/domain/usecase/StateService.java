package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.State;

import java.util.List;

public interface StateService {

    List<State> findAll();

    State findById(Long id);

    State create(State state);

    State update(State state, Long id);

    void delete(Long id);
}
