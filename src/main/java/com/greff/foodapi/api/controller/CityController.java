package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.CityAssembler;
import com.greff.foodapi.api.assembler.CityRequestDisassembler;
import com.greff.foodapi.api.model.request.CityRequest;
import com.greff.foodapi.api.model.response.CityResponse;
import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.usecase.CityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final CityAssembler cityAssembler;
    private final CityRequestDisassembler cityRequestDisassembler;

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public CityResponse findById(@PathVariable Long id) {
        var city =  cityService.findById(id);

        return cityAssembler.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponse createCity(@RequestBody @Valid CityRequest cityRequest) {
        var city = cityRequestDisassembler.toDomainObject(cityRequest);
        var cityResponse = cityService.create(city);

        return cityAssembler.toModel(cityResponse);
    }

    @PutMapping("/{id}")
    public CityResponse updateCity(@RequestBody @Valid CityRequest cityRequest, @PathVariable Long id) {
        var city = cityRequestDisassembler.toDomainObject(cityRequest);
        var cityResponse =  cityService.update(city, id);

        return cityAssembler.toModel(cityResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Long id) {
        cityService.delete(id);
    }
}