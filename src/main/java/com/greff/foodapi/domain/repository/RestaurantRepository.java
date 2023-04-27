package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

//JPA is a SPECIFICATION of JAVA EE(enterprise edition) and Bean Validation as well, so both of them talk together well, good relation
//so when an entity got bean validation, they check those validations before persisting object
//Hibernate is an IMPLEMENTATION
//inherit RestaurantRepositoryCustomizedQueries where created methods to be impl
//Repository needs to be prepared to receive specification, interface needs to inherit another class JpaSpecificationExecutor<EntityName>.
//and is an Interface to allow execution of Specification.
//got methods as 'findAll', 'findOne', 'count' that receives Specifications<EntityName> as param that we need to use.
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustomizedQueries,
        JpaSpecificationExecutor<Restaurant> {

    @NonNull
//    @Query("FROM Restaurant r JOIN FETCH r.kitchen LEFT JOIN FETCH r.paymentMethods") took it out because I'll not show this when request a resource about it
    @Query("FROM Restaurant r JOIN FETCH r.kitchen")
    List<Restaurant> findAll();

    //will query restaurants like DeliveryTax between lowerT >= and biggestT <=
    //method name is not ok to use it, that expose a lot of attributes and entities, is not conventional.
    // ex: queryByDeliveryTaxGreaterThanEqualAndDeliveryTaxLessThanEqual
    //others prefixes can be used, as 'READ', 'GET', 'QUERY' or 'STREAM', they all do the same thing as find
    //@Query("FROM Restaurant WHERE deliveryTax >= :lowerTax AND  deliveryTax <= :higherTax")
    //List<Restaurant> searchTaxByLowerTaxAndHigherTax(BigDecimal lowerTax, BigDecimal higherTax)

    //in this annotation can pass a JPQL query
    @Query("FROM Restaurant WHERE name LIKE %:name% AND kitchen.id = :id")
    //it means 'from tb restaurant where name is contains 'varName' and kitchen id equals to 'paramId'
    // find by name like 'something' and kitchen id, Param can make bind with another name to use in annotation
    List<Restaurant> searchByNameKitchenId(String name, @Param("id") Long kitchenId);

    //will get the first restaurant that get name like string 'name', making 'LIKE' and limiting at same time
    Optional<Restaurant> getFirstRestaurantByNameContaining(String name);

    //query of 2 first with name like string 'name'
    List<Restaurant> streamTop2ByNameContaining(String name);

    //query how many restaurants is there with kitchen id
    Integer countByKitchenId(Long kitchenId);

    //can be returned optionals, lists, hashmaps, others collections, but needs to make sense, those returns and stuff
}