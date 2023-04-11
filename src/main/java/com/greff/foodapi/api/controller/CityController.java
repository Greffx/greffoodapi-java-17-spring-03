package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.usecase.CityService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.http.HttpStatus;
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
        City City1 = cityService.create(city);
        return ResponseEntity.created(builder.path("/{id}").buildAndExpand(City1.getId()).toUri()).body(City1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@RequestBody City city, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(cityService.update(city, id));
        } catch (NotFoundObjectException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) {
        try {
            cityService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (NotFoundObjectException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
