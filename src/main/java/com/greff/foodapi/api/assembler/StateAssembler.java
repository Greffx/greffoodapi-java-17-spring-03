package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.StateResponse;
import com.greff.foodapi.domain.mapper.StateMapper;
import com.greff.foodapi.domain.model.State;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class StateAssembler {

    private final StateMapper stateMapper;

    public StateResponse toModel(State state) {
        return stateMapper.fromStateToStateResponse(state);
    }

    public List<StateResponse> toCollectionModel(List<State> states) {
        return states.stream().map(this::toModel).toList();
    }
}
