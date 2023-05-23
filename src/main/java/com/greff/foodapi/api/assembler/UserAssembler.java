package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.UserResponse;
import com.greff.foodapi.domain.mapper.UserMapper;
import com.greff.foodapi.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Component
public class UserAssembler {

    private final UserMapper userMapper;

    public UserResponse toModel(User user) {
        return userMapper.fromUserToUserResponse(user);
    }

    public List<UserResponse> toCollectionModel(Collection<User> users) {
        return users.stream().map(this::toModel).toList();
    }
}
