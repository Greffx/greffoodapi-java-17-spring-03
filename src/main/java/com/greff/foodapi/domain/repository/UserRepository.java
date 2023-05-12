package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
