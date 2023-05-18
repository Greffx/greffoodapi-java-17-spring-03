package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.PaymentMethodAssembler;
import com.greff.foodapi.api.model.response.PaymentMethodResponse;
import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.usecase.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/payment_methods")
public class RestaurantPaymentMethodController {

    private final RestaurantService restaurantService;
    private final PaymentMethodAssembler paymentMethodAssembler;

    @GetMapping//receiving restaurantId, to list all its payment methods
    public Set<PaymentMethodResponse> findAll(@PathVariable Long restaurantId) {//since binding is in line 15, don't need to use in line 20
        Restaurant restaurant = restaurantService.findById(restaurantId);

        return paymentMethodAssembler.toCollectionModel(restaurant.getPaymentMethods());
    }

    @PutMapping("/{paymentMethodId}")
    public void association(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.restaurantAssociationWithPaymentMethod(restaurantId, paymentMethodId);
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dissociation(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.restaurantDissociationWithPaymentMethod(restaurantId, paymentMethodId);
    }
}
