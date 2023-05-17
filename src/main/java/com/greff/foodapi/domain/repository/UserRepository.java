package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //when inheriting only my custom JPA repository, need to say specifically that this interface is a repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //optional means that could be null or could have a user
}
