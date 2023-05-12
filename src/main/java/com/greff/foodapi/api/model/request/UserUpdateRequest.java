package com.greff.foodapi.api.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
