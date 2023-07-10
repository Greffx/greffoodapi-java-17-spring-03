package com.greff.foodapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor //important to use this all args constructor because will be needed it in service impl in builder.construct method
@Setter
@Getter
public class OrderDailySells {
    //class to represent response DTO, since it hold no sensitive data, it is ok to return this one

    private LocalDate date;
    private Long totalSells;
    private BigDecimal totalProfit;

}
