package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> { //repository of kitchens, where id entity saying class name and type of id, this is from Spring Data JPA, SDJ
    //This type of repository of SDJ is to resume boilerplate code, boilerplate - repetitive code
    //LIKE means that contains part of a name, '%' joker character, will complete with that :name, ex: if receive letter 'a' will get a list of kitchens that contain letter 'a'
    @Query("SELECT k FROM Kitchen k WHERE lower(k.name) LIKE lower(concat( '%', :name, '%')) ORDER BY k.name DESC")
    //JPQL java Persistence Query Language
    List<Kitchen> findByName(@Param("name") String name); //@Param makes binding of name with :name up there

}

//repository is to be in domain level
//standard repository should clone a collection, like with method signature, like 'create', should be 'add' or 'find', should be 'findById or something'
//not should repository per entity/table, should only create repository per AGGREGATE ROOT, it's standard, a rule, should only be AGGREGATE ROOT repositories