//package com.greff.foodapi.infrastructure.repository.spec;
//
//import com.greff.foodapi.domain.model.Restaurant;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import lombok.AllArgsConstructor;
//import org.springframework.data.jpa.domain.Specification;
//
//@AllArgsConstructor
//public class RestaurantsWithSimilarNameSpec implements Specification<Restaurant> { //this class represents a restriction, filter with 'like name', 'similar name'
//
//    private String name; //we used with constructor to get param name
//
//    @Override
//    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        return criteriaBuilder.like(root.get("name"), "%" + name + "%");
//    }
//}
