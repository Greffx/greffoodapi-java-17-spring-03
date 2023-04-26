package com.greff.foodapi.domain.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.repository.KitchenRepository;
import com.greff.foodapi.domain.repository.RestaurantRepository;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
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

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, KitchenRepository kitchenRepository) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenRepository = kitchenRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() ->
                new RestaurantNotFoundException(id));
    }

    @Override
    public List<Restaurant> findByDeliveryTax(BigDecimal lower, BigDecimal higher) {
        return restaurantRepository.searchTaxByLowerTaxAndHigherTax(lower, higher);
    }

    @Override
    public List<Restaurant> findByNameAndKitchen(String name, Long kitchenId) {
        return restaurantRepository.searchByNameKitchenId(name, kitchenId);
    }

    @Override
    public List<Restaurant> findTwoRestaurantsByName(String name) {
        return restaurantRepository.streamTop2ByNameContaining(name);
    }

    @Override
    public Restaurant findFirstOneByName(String name) {
        return restaurantRepository.getFirstRestaurantByNameContaining(name).orElseThrow(() ->
                new RestaurantNotFoundException(String.format("Restaurant with name %s, not found", name)));
    }

    @Override
    public Integer findHowManyRestaurantsPerKitchen(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @Override
    public List<Restaurant> findWithFreeDeliveryTaxAndWithSimilarName(String name) {
        return restaurantRepository.findWithFreeTaxDelivery(name);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();

        try {
            Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() ->
                    new KitchenNotFoundException(kitchenId));

            restaurant.setKitchen(kitchen);

            return restaurantRepository.save(restaurant);

        } catch (NotFoundObjectException e) {
            throw new KitchenNotFoundException(kitchenId);
        }
    }

    @Override
    public Restaurant update(Restaurant restaurant, Long id) {
        try {
            Restaurant restaurantToChange = findById(id);

            restaurantToChange.setName(restaurant.getName());
            restaurantToChange.setDeliveryTax(restaurant.getDeliveryTax());
            restaurantToChange.setKitchen(restaurant.getKitchen());

            return create(restaurantToChange);

        } catch (KitchenNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void patchFields(Map<String, Object> fields, Restaurant restaurant) { //this method objective is to substitute restaurant attributes for fields attributes
        ObjectMapper objectMapper = new ObjectMapper(); //Of JACKSON, Responsible to convert JSON to Java, Java to JSON
        Restaurant restaurantSource = objectMapper.convertValue(fields, Restaurant.class); //Create of fields(source data), to Restaurant type

        fields.forEach((nameProperty, valueProperty) -> {
            //imagine passing field at request and this collection will search at first param, which field is equal.
            // Like body request will be 'name', field will find attribute to match
            Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
            field.setAccessible(true); //private fields can be accessed now

            //getting field of restaurantSource and setting field with this get. Get will get value of Field
            Object newValue = ReflectionUtils.getField(field, restaurantSource);

            //means that will get propertyName and change property value of target, for valueProperty
            ReflectionUtils.setField(field, restaurant, newValue);
        });
    }

    @Override
    public void deleteById(Long id) {
        findById(id);

        try {
            restaurantRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("Restaurant", id);
        }
    }
}
