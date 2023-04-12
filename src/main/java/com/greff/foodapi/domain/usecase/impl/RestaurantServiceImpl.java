package com.greff.foodapi.domain.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.model.PaymentMethod;
import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.repository.KitchenRepository;
import com.greff.foodapi.domain.repository.PaymentMethodRepository;
import com.greff.foodapi.domain.repository.RestaurantRepository;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    public List<Restaurant> findByDeliveryTax(BigDecimal lower, BigDecimal higher) {
        return restaurantRepository.findByDeliveryTaxGreaterThanEqualAndDeliveryTaxLessThanEqual(lower, higher);
    }

    @Override
    public List<Restaurant> findByNameAndKitchen(String name, Long kitchenId) {
        return restaurantRepository.findByNameContainingAndKitchenId(name, kitchenId);
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

    @Override
    public Restaurant update(Restaurant restaurant, Long id) {
        Restaurant restaurantToChange = findById(id);

        restaurantToChange.setName(restaurant.getName());
        restaurantToChange.setDeliveryTax(restaurant.getDeliveryTax());
        restaurantToChange.setKitchen(restaurant.getKitchen());
        restaurantToChange.setPaymentMethod(restaurant.getPaymentMethod());

        return create(restaurantToChange);
    }

    @Override
    public void patchFields(Map<String, Object> fields, Restaurant restaurant) { //this method objective is to substitute restaurant attributes for fields attributes
        ObjectMapper objectMapper = new ObjectMapper(); //Of JACKSON, Responsible to convert JSON to Java, Java to JSON
        Restaurant restaurantSource = objectMapper.convertValue(fields, Restaurant.class); //Create of fields(source data), to Restaurant type

        fields.forEach((nameProperty, valueProperty) -> {
            //imagine passing field at request and this collection will search at first param, which field is equal. Like body request will be 'name', field will find attribute to match
            Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
            field.setAccessible(true); //private fields can be accessed now

            Object newValue = ReflectionUtils.getField(field, restaurantSource); //getting field of restaurantSource and setting field with this get. Get will get value of Field

            //means that will get propertyName and change property value of target, for valueProperty
            ReflectionUtils.setField(field, restaurant, newValue);
        });
    }
}
