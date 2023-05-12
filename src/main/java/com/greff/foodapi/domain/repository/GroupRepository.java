package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
