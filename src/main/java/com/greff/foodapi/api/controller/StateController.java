package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.usecase.StateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public List<State> getList() { //It's ok to use ResponseEntity, when we need to modify a response of some request, but when we don't need, it's ok to not use too
        return stateService.findAll();
    }
}
