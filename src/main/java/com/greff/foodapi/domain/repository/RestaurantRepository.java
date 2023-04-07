package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> { //repository of kitchens, where id entity saying class name and type of id
}

//repository is to be in domain level
//standard repository should clone a collection, like with method signature, like 'create', should be 'add' or 'find', should be 'findById or something'
//not should repository per entity/table, should only create repository per AGGREGATE ROOT, it's standard, a rule, should only be AGGREGATE ROOT repositories