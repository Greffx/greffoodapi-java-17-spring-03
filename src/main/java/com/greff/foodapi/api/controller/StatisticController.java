package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.dto.OrderDailySells;
import com.greff.foodapi.domain.model.filter.DailySellsFilter;
import com.greff.foodapi.domain.usecase.SellsQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    SellsQueryService sellsQueryService;

    @GetMapping("/daily-sells")
    public List<OrderDailySells> findDailySells(DailySellsFilter dailySellsFilter,
                                                @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        //annotation to say it is ok to no specify timeSet and default value will be UTC pattern

        //since it doesn't return anything like a sensitive data, it's ok to return like this
        //it's been used just for reading, not to alter something
        return sellsQueryService.findDailySells(dailySellsFilter, timeOffSet);
    }
}
