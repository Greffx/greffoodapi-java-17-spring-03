package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Permission;
import com.greff.foodapi.domain.repository.PermissionRepository;
import com.greff.foodapi.domain.usecase.PermissionService;
import com.greff.foodapi.domain.usecase.exception.PermissioNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissioNotFoundException("Permission", id));
    }
}
