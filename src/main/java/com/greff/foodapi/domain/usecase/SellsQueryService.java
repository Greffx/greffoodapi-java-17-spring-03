package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.dto.OrderDailySells;
import com.greff.foodapi.domain.model.filter.DailySellsFilter;

import java.util.List;

public interface SellsQueryService {
    //created interface because is more like infrastructure stuff, since will use JPA to query
    //so impl of this class will be there

    List<OrderDailySells> findDailySells(DailySellsFilter dailySellsFilter, String timeOffSet);
}
