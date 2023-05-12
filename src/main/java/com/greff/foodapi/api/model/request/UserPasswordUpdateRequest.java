package com.greff.foodapi.api.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordUpdateRequest {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @Min(3)
    private String newPassword;
}
