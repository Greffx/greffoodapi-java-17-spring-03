package com.greff.foodapi.infrastructure.service.impl.report;

import com.greff.foodapi.domain.model.filter.DailySellsFilter;
import com.greff.foodapi.domain.usecase.SellsQueryService;
import com.greff.foodapi.domain.usecase.SellsReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@AllArgsConstructor
@Service
public class SellsReportServiceImpl implements SellsReportService {

    private SellsQueryService sellsQueryService;

    @Override
    public byte[] generateDailySellsPdf(DailySellsFilter dailySellsFilter, String timeOffset) throws JRException {
            //inputStream, data flow, value flow
            //getting data flow from file inside project, this is why this.getClass(), from project path
            //so, this method will search a resource inside project and return as stream(data flow)
            var inputStream = this.getClass().getResourceAsStream("/reports/daily-sells.jasper");

            //using parameters to get LOCALE to auto format how data will go, if is money, will be real
            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var dailySells = sellsQueryService.findDailySells(dailySellsFilter, timeOffset);

            //since in here is a query which return a LIST of daily-sells object java
            //'java beans' is that list, it's objects java in general
            var dataSource = new JRBeanCollectionDataSource(dailySells);

            //JasperFillManager = manager to seed a jasper report and return a file ready to go
            var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            //JasperExportManager is a manager to export a jasper file into a type, like pdf or something similar
            return JasperExportManager.exportReportToPdf(jasperPrint);

    }
}
