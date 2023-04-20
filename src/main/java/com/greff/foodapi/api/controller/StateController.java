package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.usecase.StateService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<State> createState(@RequestBody State state, UriComponentsBuilder builder) {
        State state1 = stateService.create(state);
        return ResponseEntity.created(builder.path("/{id}").buildAndExpand(state1.getId()).toUri()).body(state1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> updateState(@RequestBody State state, @PathVariable Long id) {
        return ResponseEntity.ok(stateService.update(state, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteState(@PathVariable Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
