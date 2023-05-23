package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.UserAssembler;
import com.greff.foodapi.api.model.response.UserResponse;
import com.greff.foodapi.domain.usecase.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/responsibles")
public class RestaurantUserController {

    private final RestaurantService restaurantService;
    private final UserAssembler userAssembler;


    @GetMapping
    public List<UserResponse> findAllResponsible(@PathVariable Long restaurantId) {
        var responsibles = restaurantService.findById(restaurantId).getResponsibles();

        return userAssembler.toCollectionModel(responsibles);
    }

    @DeleteMapping("/{responsbileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateResponsible(@PathVariable Long restaurantId, @PathVariable Long responsbileId) {
        restaurantService.disassociateResponsible(restaurantId, responsbileId);
    }

    @PutMapping("/{responsbileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateResponsible(@PathVariable Long restaurantId, @PathVariable Long responsbileId) {
        restaurantService.associateResponsible(restaurantId, responsbileId);
    }

}
