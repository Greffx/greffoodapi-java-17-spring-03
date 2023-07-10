package com.greff.foodapi.domain.usecase;

import com.greff.foodapi.domain.model.filter.DailySellsFilter;
import net.sf.jasperreports.engine.JRException;

public interface SellsReportService {

    byte[] generateDailySellsPdf(DailySellsFilter dailySellsFilter, String timeOffset) throws JRException;
}
