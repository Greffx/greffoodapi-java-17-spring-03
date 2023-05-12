package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Group;

import java.util.List;

public interface GroupService {

    Group create(Group group);

    Group update(Group group);

    Group findById(Long id);

    List<Group> findAll();

    void deleteById(Long id);
}
