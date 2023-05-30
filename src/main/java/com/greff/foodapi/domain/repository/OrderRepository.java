package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//need to inherit this specification interface to work with spec factor methods
public interface OrderRepository  extends CustomJpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @NonNull
    //JOIN FETCH is used to eagerly fetch associated entities along with the main entity in a single query
    //means that the data for the User entity will be retrieved in the same query
    //instead of generating separate queries for each Order entity
    //@ManyToOne associations are fetched lazily by default
    //If I want to fetch the associated entity eagerly, you can use JOIN FETCH in your query.
    @Query("FROM Order o JOIN FETCH o.user JOIN FETCH o.restaurant")
    List<Order> findAll();

    @Query("SELECT o FROM Order o WHERE o.uuid = :uuid")
    Optional<Order> findByUuid(String uuid);
}
