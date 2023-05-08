package com.greff.foodapi.api.assembler; //assembler is like a builder, converter, transformer
//package used to transform entity to model representation

import com.greff.foodapi.api.model.response.CityResponse;
import com.greff.foodapi.domain.mapper.CityMapper;
import com.greff.foodapi.domain.model.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component //to work, needs to be a spring component
public class CityAssembler {

    private final CityMapper cityMapper;

    public CityResponse toModel(City city) {
        return cityMapper.fromCityToCityResponse(city);
    }
}
