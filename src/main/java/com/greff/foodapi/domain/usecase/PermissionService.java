package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.Permission;

public interface PermissionService {

    Permission findById(Long id);
}
