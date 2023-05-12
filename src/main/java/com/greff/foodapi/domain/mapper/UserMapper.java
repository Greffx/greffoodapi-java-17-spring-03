package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.UserPasswordUpdateRequest;
import com.greff.foodapi.api.model.request.UserRequest;
import com.greff.foodapi.api.model.request.UserUpdateRequest;
import com.greff.foodapi.api.model.response.UserResponse;
import com.greff.foodapi.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse fromUserToUserResponse(User user);

    User fromUserRequestToUser(UserRequest userRequest);

    void updateDomainObject(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    @Mapping(target = "user.password", source = "userPasswordUpdateRequest.newPassword")
    void updateUserPasswordDomainObject(UserPasswordUpdateRequest userPasswordUpdateRequest, @MappingTarget User user);
}
