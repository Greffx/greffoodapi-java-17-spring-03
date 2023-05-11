package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.AddressRequest;
import com.greff.foodapi.api.model.response.AddressResponse;
import com.greff.foodapi.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = CityMapper.class)
public interface AddressMapper {

    @Named("toAddress")
    @Mapping(target = "city.id", source = "cityIdRefRequest.id")
    Address fromAddressRequestToAddress(AddressRequest addressRequest);

    @Named("toAddressResponse")
    @Mapping(target = "simpleCityResponse", source = "city", qualifiedByName = "toSimpleCityResponse")
    AddressResponse fromAddressToAddressResponse(Address address);
}
