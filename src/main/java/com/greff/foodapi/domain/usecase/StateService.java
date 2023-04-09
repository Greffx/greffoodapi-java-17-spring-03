package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.State;

import java.util.List;

public interface StateService {

    List<State> findAll();
}
