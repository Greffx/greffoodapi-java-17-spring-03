package com.greff.foodapi.domain.usecase.impl;

import com.greff.foodapi.domain.model.User;
import com.greff.foodapi.domain.repository.UserRepository;
import com.greff.foodapi.domain.usecase.UserService;
import com.greff.foodapi.domain.usecase.exception.BusinessException;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.InvalidUserPasswordException;
import com.greff.foodapi.domain.usecase.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        return create(user);
    }

//    @Transactional
    @Override
    public void updatePassword(User user, boolean passwordVerification) {
        try {
            if (!passwordVerification)
                throw new InvalidUserPasswordException("Current password did not match, try again");

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
}
