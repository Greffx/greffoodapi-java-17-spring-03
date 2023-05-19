package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.PermissionResponse;
import com.greff.foodapi.domain.mapper.PermissionMapper;
import com.greff.foodapi.domain.model.Permission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PermissionAssembler {

    private final PermissionMapper permissionMapper;

    public PermissionResponse toModel(Permission permission) {
        return permissionMapper.fromPermissionToPermissionResponse(permission);
    }

    public Set<PermissionResponse> toCollectionModel(Set<Permission> permissions) {
        return permissions.stream().map(this::toModel).collect(Collectors.toSet());
    }
}
