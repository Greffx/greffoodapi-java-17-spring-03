package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.response.PermissionResponse;
import com.greff.foodapi.domain.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponse fromPermissionToPermissionResponse(Permission permission);
}
