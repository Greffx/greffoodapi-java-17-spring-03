package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.usecase.CityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

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
    public City createCity(@RequestBody @Valid City city) {
        return cityService.create(city);
    }

    @PutMapping("/{id}")
    public City updateCity(@RequestBody @Valid City city, @PathVariable Long id) {
        return cityService.update(city, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Long id) {
        cityService.delete(id);
    }
}