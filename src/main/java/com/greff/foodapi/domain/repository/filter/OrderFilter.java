package com.greff.foodapi.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

//this class is a DTO, to represent filters from controllers requests, URL query params
@Getter
@Setter
public class OrderFilter {

    //those properties could or not to be in a query for order
    //so can query by clientId and restaurantId or just client id or both, or use all of them or just 1 or 2 or 3
    private Long clientId;
    private Long restaurantId;

    //this annotation will force to format like what is inside, just pass mouse arrow to see
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime initCreationDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime endCreationDate;

}
