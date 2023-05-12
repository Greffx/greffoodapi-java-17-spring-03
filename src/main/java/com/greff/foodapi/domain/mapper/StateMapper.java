package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.StateRequest;
import com.greff.foodapi.api.model.response.StateResponse;
import com.greff.foodapi.domain.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StateMapper {

    @Named("ToStateResponse")
    //@Named indicate which method with the same name in the mapper class should be used for a specific mapping.
    //When using the qualifiedByName on another class method
    //attributed in @Mapping, you can specify the name of the method to be used as a qualifier.
    StateResponse fromStateToStateResponse(State state);

    State fromStateRequestToState(StateRequest stateRequest);

    void updateDomainObject(StateRequest stateRequest, @MappingTarget State state);
}


