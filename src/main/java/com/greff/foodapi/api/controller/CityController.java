package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.usecase.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<City> createCity(@RequestBody City city, UriComponentsBuilder builder) {
        City city1 = cityService.create(city);
        return ResponseEntity.created(builder.path("/{id}").buildAndExpand(city1.getId()).toUri()).body(city1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@RequestBody City city, @PathVariable Long id) {
        return ResponseEntity.ok(cityService.update(city, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}