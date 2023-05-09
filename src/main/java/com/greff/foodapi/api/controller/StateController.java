package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.StateAssembler;
import com.greff.foodapi.api.assembler.StateRequestDisassembler;
import com.greff.foodapi.api.model.request.StateRequest;
import com.greff.foodapi.api.model.response.StateResponse;
import com.greff.foodapi.domain.usecase.StateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;
    private final StateAssembler stateAssembler;
    private final StateRequestDisassembler stateRequestDisassembler;

    @GetMapping
    public List<StateResponse> findAll() {
        var states = stateService.findAll();

        return stateAssembler.toCollectionModel(states);
    }

    @GetMapping("/{id}")
    public StateResponse findById(@PathVariable Long id) {
        var state = stateService.findById(id);

        return stateAssembler.toModel(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateResponse createState(@RequestBody @Valid StateRequest stateRequest) {
        var state = stateRequestDisassembler.toDomainObject(stateRequest);
        var stateResponse = stateService.create(state);

        return stateAssembler.toModel(stateResponse);
    }

    @PutMapping("/{id}")
    public StateResponse updateState(@RequestBody @Valid StateRequest stateRequest, @PathVariable Long id) {
        var state = stateRequestDisassembler.toDomainObject(stateRequest);
        var stateResponse = stateService.update(state, id);

        return stateAssembler.toModel(stateResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteState(@PathVariable Long id) {
        stateService.delete(id);
    }
}
