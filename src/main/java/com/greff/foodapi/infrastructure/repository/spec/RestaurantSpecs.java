package com.greff.foodapi.infrastructure.repository.spec;

import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs { //this is a utility class, they call this factory of specifications

    private RestaurantSpecs() {
    }

    public static Specification<Restaurant> withFreeDeliveryTax() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deliveryTax"), BigDecimal.ZERO));
        //meaning 'from Restaurant where deliveryTax = 0' and will create a predicate
    }

    public static Specification<Restaurant> withSimilarName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }
}
