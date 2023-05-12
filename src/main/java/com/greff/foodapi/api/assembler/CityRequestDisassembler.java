package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.CityRequest;
import com.greff.foodapi.domain.mapper.CityMapper;
import com.greff.foodapi.domain.model.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CityRequestDisassembler {
    //class used to transform model representation to domain object

    private final CityMapper cityMapper;

    public City toDomainObject(CityRequest cityRequest) {
        return cityMapper.fromCityRequestToCity(cityRequest);
    }

    public void updateCityDomainObject(CityRequest cityRequest, City city) {
        cityMapper.updateDomainObject(cityRequest, city);
    }
}
