package com.greff.foodapi.infrastructure.service.impl;

import com.greff.foodapi.domain.enums.OrderStatus;
import com.greff.foodapi.domain.model.Order;
import com.greff.foodapi.domain.model.dto.OrderDailySells;
import com.greff.foodapi.domain.model.filter.DailySellsFilter;
import com.greff.foodapi.domain.usecase.SellsQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class SellsQueryServiceImpl implements SellsQueryService {

    public static final String CREATION_DATE = "creationDate";
    public static final String STATUS = "status";

    @PersistenceContext //creating an instance JPA entity manager to get criteria builder
    EntityManager manager;

    @Override
    //important to understand this method don't need to ONLY return queries, can transform objects too, work with objects or stuff similar to this
    //like a service impl
    public List<OrderDailySells> findDailySells(DailySellsFilter dailySellsFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder(); //used to create queries, criteria, aggregate functions and more
        //this is the criteria builder factor, used to create a criteria instance

        //creating a criteriaQuery with builder and method with class type
        //this class type will represent the return type, when this query is in action the expected return will be a OrderDailySells.class type
        //don't need to be an entity class, just need a class to represent result
        CriteriaQuery<OrderDailySells> query = builder.createQuery(OrderDailySells.class);
        Root<Order> orderRoot = query.from(Order.class); //specifying clause that will be in query, like 'FROM Order'

        ArrayList<Predicate> predicates = new ArrayList<>();
        //Predicate represents a condition or a restriction that can be applied to a query
        //It is used to specify the filtering criteria of the query

        List<OrderStatus> statusList = Arrays.asList(OrderStatus.CONFIRMED, OrderStatus.DELIVERED);
        //status list to use IN function of predicate interface

        if (dailySellsFilter.getRestaurantId() != null) {
            predicates.add(builder.and(
                            builder.equal(orderRoot.get("restaurant"), dailySellsFilter.getRestaurantId()),
                            orderRoot.get(STATUS).in(statusList)
                            //will make query equal to what you did and get where status is similar to that list
                    )
            );
        }

        if (dailySellsFilter.getInitDateCreation() != null) {
            predicates.add(builder.and(
                            builder.greaterThanOrEqualTo(orderRoot.get(CREATION_DATE), dailySellsFilter.getInitDateCreation()),
                            orderRoot.get(STATUS).in(statusList)
                    )

            );
        }

        if (dailySellsFilter.getEndDateCreation() != null) {
            predicates.add(builder.and(
                            builder.lessThanOrEqualTo(orderRoot.get(CREATION_DATE), dailySellsFilter.getEndDateCreation()),
                            orderRoot.get(STATUS).in(statusList)
                    )
            );
        }

        //builder.function() from interface EXPRESSION will create an expression to execute a function from database
        //first param is name function in database that will be used,
        //second param is what type to expected to return in java and
        //third param is which property to use for this function
        Expression<Date> dateFunctionCreationDate = builder.function("DATE", Date.class, orderRoot.get(CREATION_DATE));
        //Expression represents a value or an expression that can be used in the selection, grouping, or filtering of query results

        //this one means that select will be used in construct from another class
        //so this method is, from selections, build a constructor from this class
        //this is dynamic instantiation
        Selection<OrderDailySells> selection = builder.construct(OrderDailySells.class,
                dateFunctionCreationDate, builder.count(orderRoot.get("id")), builder.sum(orderRoot.get("total"))
                //this selections will seed constructor from OrderDailySells.class
        );

        //projecting which select is used, with where with dynamic predicates(filters) and grouped by property
        query.select(selection).where(predicates.toArray(new Predicate[0])).groupBy(dateFunctionCreationDate);

        return manager.createQuery(query).getResultList(); //create query and then return as list

    }
}
