//package com.greff.foodapi.infrastructure.repository.spec;
////'INFRASTRUCTURE LAYER' -  package, will gather classes of implementation, technic of how to access data of DB, there is nothing to do with business rules of application
////THERE IS NOTHING TO DO WITH business of restaurants. Infrastructure manages: how to send an email, how to connect to DB
////So how implementation of repositories is lower level code compared with business rules code, it goes here, makes more sense to be infrastructure business
////It is in spec because we're using specifications interface
////goes inside infrastructure because 'SPECIFICATION' interface implementation got code that makes directly communication with JPA API,
// and we're considering this like infrastructure

//import com.greff.foodapi.domain.model.Restaurant;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.math.BigDecimal;
//
//public class FreeDeliveryTaxRestaurantsSpec implements Specification<Restaurant> { //this class represents a restriction, filter with free delivery tax
//
//    //METHOD - create predicate(predicate is like 'where x = y and y > 3 or c like % b %', is a predicate/filter)
//
//    //CRITERIABUILDER - criteriaBuilder.equal(root.get("deliveryTax"), BigDecimal.ZERO)
//    //To get an instance of 'CRITERIAQUERY' we need to use CriteriaBuilder, which is an interface too. Work as a factory to build elements that we need to create queries.
//    // So before use 'CRITERIAQUERY' we need to instance criteriaBuilder
//    //BigDecimal.ZERO is the second param, it should be what we need to filter, will match with root.get
//
//    //ROOT - Root<EntityName> means like 'from <EntityName>', so would be like 'from Restaurant'
//    //root.get("attribute of entity") getting representation of property ("attribute of entity") inside entity root that we choose as type
//
//    //CRITERIAQUERY - CriteriaQuery is a JPA API very useful to create queries programming, got a lot of boilerplate or bureaucracy, but helps with complex queries
//    //CriteriaQuery responsible to build clauses. is a constructor of clauses. clauses can be like 'SELECT', 'WHERE', 'FROM'
//    @Override
//    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        return criteriaBuilder.equal(root.get("deliveryTax"), BigDecimal.ZERO); //meaning 'from Restaurant where deliveryTax = 0' and will create a predicate
//    }
//}
