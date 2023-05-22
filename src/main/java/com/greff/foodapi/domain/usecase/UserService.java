package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    void updatePassword(User user, boolean passwordVerification);

    User findById(Long id);

    List<User> findAll();

    void delete(Long id);

    void groupDisassociation(Long userId, Long groupId);

    void groupAsassociation(Long userId, Long groupId);
}