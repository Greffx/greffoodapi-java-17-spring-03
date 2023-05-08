package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.model.request.CityRequest;
import com.greff.foodapi.api.model.response.CityResponse;
import com.greff.foodapi.domain.mapper.CityMapper;
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
    private final CityMapper cityMapper;

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponse createCity(@RequestBody @Valid CityRequest cityRequest) {
        var city = cityMapper.fromCityRequestToCity(cityRequest);
        var cityResponse = cityService.create(city);

        return cityMapper.fromCityToCityResponse(cityResponse);
    }

    @PutMapping("/{id}")
    public CityResponse updateCity(@RequestBody @Valid CityRequest cityRequest, @PathVariable Long id) {
        var city = cityMapper.fromCityRequestToCity(cityRequest);
        var cityResponse =  cityService.update(city, id);

        return cityMapper.fromCityToCityResponse(cityResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Long id) {
        cityService.delete(id);
    }
}