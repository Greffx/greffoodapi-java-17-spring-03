package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.Group;
import com.greff.foodapi.domain.model.User;
import com.greff.foodapi.domain.repository.UserRepository;
import com.greff.foodapi.domain.usecase.GroupService;
import com.greff.foodapi.domain.usecase.UserService;
import com.greff.foodapi.domain.usecase.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GroupService groupService;

    @Transactional
    @Override
    public User create(User user) {
        userRepository.detach(user); //method to JPA not manage this instance

        var optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent() && !optionalUser.get().equals(user))
            //if there's a user, BUT different from param user, not same user as optional, then throw exception
            throw new EmailAlreadyRegisterException("This email is been used, choose another one");

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        return create(user);
    }

    @Transactional
    @Override
    public void updatePassword(User user, boolean passwordVerification) {
        try {
            if (!passwordVerification)
                throw new InvalidUserPasswordException("Current password did not match, try again");
            //business rule to not let update password if not getting current one right

            create(user);

        } catch (InvalidUserPasswordException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User", id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        findById(id);

        try {
            userRepository.deleteById(id);


        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("User", id);
        }
    }

    @Transactional
    @Override
    public void groupDisassociation(Long userId, Long groupId) {
        User user = findById(userId);
        Group group = groupService.findById(groupId);

        user.disassociateGroup(group);
    }

    @Transactional
    @Override
    public void groupAsassociation(Long userId, Long groupId) {
        User user = findById(userId);
        Group group = groupService.findById(groupId);

        user.associateGroup(group);
    }
}