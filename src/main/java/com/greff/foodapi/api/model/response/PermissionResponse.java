package com.greff.foodapi.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionResponse {

    private Long id;
    private String name;
    private String description;
}