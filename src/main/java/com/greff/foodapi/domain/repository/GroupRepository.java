package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CustomJpaRepository<Group, Long> {
}
