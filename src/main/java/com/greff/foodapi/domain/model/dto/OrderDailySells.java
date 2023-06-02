package com.greff.foodapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor //important to use this all args constructor because will be needed it in service impl in builder.construct method
@Setter
@Getter
public class OrderDailySells {
    //class to represent response DTO, since it hold no sensitive data, it is ok to return this one

    private Date date;
    private Long totalSells;
    private BigDecimal totalProfit;

}
