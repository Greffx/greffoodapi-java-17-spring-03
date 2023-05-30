package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.PermissionResponse;
import com.greff.foodapi.domain.mapper.PermissionMapper;
import com.greff.foodapi.domain.model.Permission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Component
public class PermissionAssembler {

    private final PermissionMapper permissionMapper;

    public PermissionResponse toModel(Permission permission) {
        return permissionMapper.fromPermissionToPermissionResponse(permission);
    }

    public List<PermissionResponse> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream().map(this::toModel).toList();
    }
}
