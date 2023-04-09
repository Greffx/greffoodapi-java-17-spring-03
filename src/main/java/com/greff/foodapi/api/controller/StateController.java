package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.usecase.StateService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<State>> getList() {
        return ResponseEntity.ok().body(stateService.findAll());
    }
}
