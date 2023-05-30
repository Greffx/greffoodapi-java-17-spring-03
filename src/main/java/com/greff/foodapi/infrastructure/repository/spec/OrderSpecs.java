package com.greff.foodapi.infrastructure.repository.spec;

import com.greff.foodapi.domain.model.Order;
import com.greff.foodapi.domain.repository.filter.OrderFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;


public class OrderSpecs {

    //Utility classes, which are collections of static members, are not meant to be instantiated
    //Even abstract utility classes, which can be extended, should not have public constructors
    private OrderSpecs() {
    }

    public static Specification<Order> usingFilters(OrderFilter orderFilter) { //returning an order dynamic specification query
        return (root, query, criteriaBuilder) -> { //anonymous method, so a lambda

            //to resolve many queries, to only fetch all in one query use fetch, very similar to the one that I used in repository
            root.fetch("restaurant").fetch("kitchen");
            //one attribute fetched can fetch another attribute if necessary
            root.fetch("user");

            var predicates = new ArrayList<Predicate>(); //creating a predicate arrayList, will hold several predicates
            //this list hold a list of query filters, like name equals to something or value greater or equal to something

            if (orderFilter.getClientId() != null) //if attribute is not null
                predicates.add(criteriaBuilder.equal(root.get("user"), orderFilter.getClientId())); //seed the predicate list
                //criteriaBuilder
                //like a factor, to construct criteria queries, compound selections, expressions, predicates, orderings
                //create an instance of criteriaQuery

            if (orderFilter.getRestaurantId() != null)
                predicates.add(criteriaBuilder.equal(root.get("restaurant"), orderFilter.getRestaurantId()));

            if (orderFilter.getInitCreationDate() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), orderFilter.getInitCreationDate()));
//
//            if (orderFilter.getEndCreationDate() != null)
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), orderFilter.getEndCreationDate()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0])); //need to return a 'Predicate...', which is a varargs
            //so to be a varargs need to instance like an Array, and this is a way to do this '.toArray(new Predicate[0])',
            //will return a complete Array of this ArrayList
            //and to make like name and id and cpf. like that
        };
    }
}
