package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.UserPasswordUpdateRequest;
import com.greff.foodapi.api.model.request.UserRequest;
import com.greff.foodapi.api.model.request.UserUpdateRequest;
import com.greff.foodapi.domain.mapper.UserMapper;
import com.greff.foodapi.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserRequestDisassembler {

    private final UserMapper userMapper;

    public User toDomainObject(UserRequest userRequest) {
        return userMapper.fromUserRequestToUser(userRequest);
    }

    public void updateUserDomainObject(UserUpdateRequest userUpdateRequest, User user) {
        userMapper.updateDomainObject(userUpdateRequest, user);
    }

    public void updateUserPasswordDomainObject(UserPasswordUpdateRequest userPasswordUpdateRequest, User user) {
        userMapper.updateUserPasswordDomainObject(userPasswordUpdateRequest, user);
    }
}
