package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.City;
import com.greff.foodapi.domain.model.State;
import com.greff.foodapi.domain.repository.CityRepository;
import com.greff.foodapi.domain.repository.StateRepository;
import com.greff.foodapi.domain.usecase.CityService;
import com.greff.foodapi.domain.usecase.exception.BusinessException;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
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
                new NotFoundObjectException(String.format("City with id %d, not found", id)));
    }

    @Override
    public City create(City city) {
        Long stateId = city.getState().getId();

        try {
            State state = stateRepository.findById(stateId).orElseThrow(() ->
                    new NotFoundObjectException(String.format("State with id %d, not found", stateId)));
            city.setState(state);
            //when request body of create or update is necessary and user put a state id that doesn't exist it's better to give a 400 bad request
            //because there's states, but user used the wrong id
        } catch (NotFoundObjectException e) {
            throw new BusinessException(e.getMessage());
        }

        return cityRepository.save(city);
    }

    @Override
    public City update(City city, Long id) {
        City cityToChange = findById(id);

        cityToChange.setName(city.getName());
        cityToChange.setState(city.getState());

        try {
            return create(cityToChange);

        } catch (NotFoundObjectException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            findById(id);
            cityRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Can't remove city with id %d when it's been used in another place", id));
        }
    }
}
