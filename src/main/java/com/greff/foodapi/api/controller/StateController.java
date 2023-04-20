package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.usecase.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public List<State> findAll() {
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    public State findById(@PathVariable Long id) {
        return stateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State createState(@RequestBody State state, UriComponentsBuilder builder) {
        return stateService.create(state);
    }

    @PutMapping("/{id}")
    public State updateState(@RequestBody State state, @PathVariable Long id) {
        return stateService.update(state, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteState(@PathVariable Long id) {
        stateService.delete(id);
    }
}
