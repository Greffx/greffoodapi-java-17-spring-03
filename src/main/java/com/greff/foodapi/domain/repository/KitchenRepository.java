package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> { //repository of kitchens, where id entity saying class name and type of id
}

//repository is to be in domain level
