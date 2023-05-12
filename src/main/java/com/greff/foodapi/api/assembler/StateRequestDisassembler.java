package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.StateRequest;
import com.greff.foodapi.domain.mapper.StateMapper;
import com.greff.foodapi.domain.model.State;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class StateRequestDisassembler {

    private final StateMapper stateMapper;

    public State toDomainObject(StateRequest stateRequest) {
        return stateMapper.fromStateRequestToState(stateRequest);
    }

    public void updateStateDomainObject(StateRequest stateRequest, State state) {
        stateMapper.updateDomainObject(stateRequest, state);
    }
}
