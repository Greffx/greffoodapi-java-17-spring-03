package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.model.PaymentMethod;
import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.repository.KitchenRepository;
import com.greff.foodapi.domain.repository.PaymentMethodRepository;
import com.greff.foodapi.domain.repository.RestaurantRepository;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRepository kitchenRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, KitchenRepository kitchenRepository, PaymentMethodRepository paymentMethodRepository) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenRepository = kitchenRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() ->
                new NotFoundObjectException(String.format("Restaurant with id %d, not found", id)));
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Long paymentMethodId = restaurant.getPaymentMethod().getId();

        Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() ->
                new NotFoundObjectException(String.format("kitchen with id %d, not found", kitchenId)));
        restaurant.setKitchen(kitchen);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow(() ->
                new NotFoundObjectException(String.format("payment method with id %d, not found", paymentMethodId)));

        restaurant.setKitchen(kitchen);
        restaurant.setPaymentMethod(paymentMethod);

        return restaurantRepository.save(restaurant);
    }
}
