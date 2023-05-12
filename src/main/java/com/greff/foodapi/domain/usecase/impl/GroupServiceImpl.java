package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Group;
import com.greff.foodapi.domain.repository.GroupRepository;
import com.greff.foodapi.domain.usecase.GroupService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.GroupNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    @Override
    public Group update(Group group) {
        return create(group);
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Group", id));
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        findById(id);

        try {
            groupRepository.deleteById(id);
            groupRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("Group", id);
        }
    }
}
