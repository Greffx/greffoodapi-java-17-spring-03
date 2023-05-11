package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.CityRequest;
import com.greff.foodapi.api.model.response.CityResponse;
import com.greff.foodapi.api.model.response.SimpleCityResponse;
import com.greff.foodapi.domain.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = StateMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

    @Mapping(target = "state.id", source = "stateIdRefRequest.id")
    City fromCityRequestToCity(CityRequest cityRequest);

    @Mapping(target = "stateResponse", source = "state", qualifiedByName = "ToStateResponse")
    CityResponse fromCityToCityResponse(City city);

    @Named("toSimpleCityResponse")
    @Mapping(target = "state", source = "state.name")
    SimpleCityResponse fromCityToSimpleCityResponse(City city);
}