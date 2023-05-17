package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CustomJpaRepository<Permission, Long> {
}