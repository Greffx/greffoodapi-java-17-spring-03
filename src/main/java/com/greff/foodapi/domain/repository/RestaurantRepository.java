package com.greff.foodapi.domain.repository;

import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
//JpaSpecificationExecutor<Type of class> it got methods as findAll, findOne, count that receives Specifications<Type of class>
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {

    //will query restaurants like DeliveryTax between lowerT >= and biggestT <=
    //method name is not ok to use it, that expose a lot of attributes and entities, is not conventional.
    // ex: queryByDeliveryTaxGreaterThanEqualAndDeliveryTaxLessThanEqual
    //others prefixes can be used, as 'READ', 'GET', 'QUERY' or 'STREAM', they all do the same thing as find
    @Query("FROM Restaurant WHERE deliveryTax >= :lowerTax AND  deliveryTax <= :higherTax")
    List<Restaurant> searchTaxByLowerTaxAndHigherTax(BigDecimal lowerTax, BigDecimal higherTax);

    //in this annotation can pass a JPQL query
    @Query("FROM Restaurant WHERE name LIKE %:name% AND kitchen.id = :id") //it means 'from tb restaurant where name is contains 'varName' and kitchen id equals to 'paramId'
        // find by name like 'something' and kitchen id, Param can make bind with another name to use in annotation
    List<Restaurant> searchByNameKitchenId(String name, @Param("id") Long kitchenId);

    //will get the first restaurant that get name like string 'name', making 'LIKE' and limiting at same time
    Optional<Restaurant> getFirstRestaurantByNameContaining(String name);

    //query of 2 first with name like string 'name'
    List<Restaurant> streamTop2ByNameContaining(String name);

    //query how many restaurants is there with kitchen id
    Integer countByKitchenId(Long kitchenId);

    //can be returned optionals, lists, hashmaps, others collections, but needs to make sense
}