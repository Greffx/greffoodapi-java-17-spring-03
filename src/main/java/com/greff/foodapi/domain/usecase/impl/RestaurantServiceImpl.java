package com.greff.foodapi.domain.usecase.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.model.Restaurant;
import com.greff.foodapi.domain.repository.RestaurantRepository;
import com.greff.foodapi.domain.usecase.CityService;
import com.greff.foodapi.domain.usecase.KitchenService;
import com.greff.foodapi.domain.usecase.RestaurantService;
import com.greff.foodapi.domain.usecase.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenService kitchenService;
    private final CityService cityService;
//    private final PaymentMethodService paymentMethodService

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
                new RestaurantNotFoundException(name));
    }

    @Override
    public Integer findHowManyRestaurantsPerKitchen(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @Override
    public List<Restaurant> findWithFreeDeliveryTaxAndWithSimilarName(String name) {
        return restaurantRepository.findWithFreeTaxDelivery(name);
    }

    @Transactional
    @Override
    public Restaurant create(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        City city = cityService.findById(cityId);

        Kitchen kitchen = kitchenService.findById(kitchenId);

        restaurant.setKitchen(kitchen);
        restaurant.getAddress().setCity(city);

        return restaurantRepository.save(restaurant);
    }

    @Transactional //need it to be transactional, since will alter a data of a column in database
    @Override
    public void activation(Long id) {
        Restaurant restaurant = findById(id);
        //since method findById of simpleJPARepository makes instance object managed/monitored
        //any modification to this instance when it's managed by
        //JPA context will be synchronized with database, so if anyone alters like
        //setActive, setName or any of that, don't need to be saved
        //JPA understands the instance got new attributes, synchronize with database and makes UPDATE function
        restaurant.activate(); //method in restaurant class that makes easiest to read, but it's the same as setActive(true)
    }

    @Transactional
    @Override
    public void deactivation(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.deactivate();
    }

    @Transactional
    @Override
    public Restaurant update(Restaurant restaurant) {
        try {
            return create(restaurant);

        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void patchFields(Map<String, Object> fields, Restaurant restaurant, HttpServletRequest request) {
        //this method objective is to substitute restaurant attributes for fields attributes
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper(); //Of JACKSON, Responsible to convert JSON to Java, Java to JSON
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true); //setting object mapper to fail when using ignored properties

            Restaurant restaurantSource = objectMapper.convertValue(fields, Restaurant.class); //Create of fields(source data), to Restaurant type

            fields.forEach((nameProperty, valueProperty) -> {
                //imagine passing field at request and this collection will search at first param, which field is equal.
                // Like body request will be 'name', field will find attribute to match
                Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
                field.setAccessible(true); //private fields can be accessed now

                //getting field of restaurantSource and setting newValue with this get. Get will get value of Field
                Object newValue = ReflectionUtils.getField(field, restaurantSource);

                //means that will get propertyName and change property value of target, for valueProperty
                ReflectionUtils.setField(field, restaurant, newValue);
            });

            restaurantRepository.save(restaurant);

        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
            //need it to use one with throwable, so this method wasn't deprecated, that's why need it serverHttpRequest
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        findById(id);

        try {
            restaurantRepository.deleteById(id);
            restaurantRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("Restaurant", id);
        }
    }

    @Transactional
    @Override
    public void removePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findById(restaurantId);



        restaurant.getPaymentMethods().remove();
    }
}