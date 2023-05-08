package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.CityRequest;
import com.greff.foodapi.api.model.response.CityResponse;
import com.greff.foodapi.domain.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring") //@Mapper - annotation tha marks interface as mapping interface to spring
@Service
public interface CityMapper {

    @Mapping(target = "state.id", source = "stateIdRefRequest.id")
    City fromCityRequestToCity(CityRequest cityRequest);

    @Mapping(target = "stateResponse.id", source = "state.id")
    @Mapping(target = "stateResponse.name", source = "state.name")
    CityResponse fromCityToCityResponse(City city);
}
