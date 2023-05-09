package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.StateRequest;
import com.greff.foodapi.api.model.response.StateResponse;
import com.greff.foodapi.domain.model.State;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface StateMapper {
    
    StateResponse fromStateToStateResponse(State state);

    State fromStateRequestToState(StateRequest stateRequest);
}


