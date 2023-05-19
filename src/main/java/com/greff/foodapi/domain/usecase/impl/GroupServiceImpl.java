package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Group;
import com.greff.foodapi.domain.repository.GroupRepository;
import com.greff.foodapi.domain.repository.PermissionRepository;
import com.greff.foodapi.domain.usecase.GroupService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.GroupNotFoundException;
import com.greff.foodapi.domain.usecase.exception.PermissioNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    public static final String GROUP_RESOURCE_NAME = "Group";
    private final GroupRepository groupRepository;
    private final PermissionRepository permissionRepository;

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
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(GROUP_RESOURCE_NAME, id));
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
            throw new EntityInUseException(GROUP_RESOURCE_NAME, id);
        }
    }

    @Transactional
    @Override
    public void disassociatePermission(Long groupId, Long permissionId) {
        var group = findById(groupId);

        var permission = group.getPermission().stream().filter(
                groupPermission -> groupPermission.getId().equals(permissionId)).findFirst()
                .orElseThrow(() -> new PermissioNotFoundException("Permission", permissionId, GROUP_RESOURCE_NAME, groupId));

        group.permissionDisassociation(permission);
    }

    @Transactional
    @Override
    public void associatePermission(Long groupId, Long permissionId) {
        var group = findById(groupId);

        var permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissioNotFoundException("Permission", permissionId, GROUP_RESOURCE_NAME, groupId));

        group.permissionAsassociation(permission);

    }
}
