package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //optional means that could be null or could have a user
}
