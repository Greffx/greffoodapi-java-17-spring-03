package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.repository.CityRepository;
import com.greff.foodapi.domain.repository.StateRepository;
import com.greff.foodapi.domain.usecase.CityService;
import com.greff.foodapi.domain.usecase.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public CityServiceImpl(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(() ->
                new CityNotFoundException(id));
    }

    @Override
    public City create(City city) {
        Long stateId = city.getState().getId();

        try {
            State state = stateRepository.findById(stateId).orElseThrow(() ->
                    new StateNotFoundException(stateId));

            city.setState(state);
            //when request body of create or update is necessary and user put a state id that doesn't exist it's better to give a 400 bad request
            //because there's states, but user used the wrong id
        } catch (NotFoundObjectException e) {
            throw new StateNotFoundException(stateId);
        }

        return cityRepository.save(city);
    }

    @Override
    public City update(City city) {
        try {
            return create(city);

        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        findById(id);

        try {
            cityRepository.deleteById(id);
            cityRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("City", id);
        }
    }
}
