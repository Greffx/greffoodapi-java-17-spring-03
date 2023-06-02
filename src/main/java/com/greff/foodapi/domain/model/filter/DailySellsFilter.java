package com.greff.foodapi.domain.model.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DailySellsFilter {

    private Long restaurantId;
    private LocalDate initDateCreation;
    private LocalDate endDateCreation;

}
