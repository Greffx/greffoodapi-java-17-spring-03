package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<Permission, Long> {
}