package com.greff.foodapi.api.controller;

import com.greff.foodapi.domain.model.dto.OrderDailySells;
import com.greff.foodapi.domain.model.filter.DailySellsFilter;
import com.greff.foodapi.domain.usecase.SellsQueryService;
import com.greff.foodapi.domain.usecase.SellsReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    SellsReportService reportService;

    //this method will be used when user want to generate json value
    @GetMapping(path = "/daily-sells", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDailySells> findDailySells(DailySellsFilter dailySellsFilter,
                                                @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        //annotation to say it is ok to no specify timeSet and default value will be UTC pattern

        //since it doesn't return anything like a sensitive data, it's ok to return like this
        //it's been used just for reading, not to alter something
        return sellsQueryService.findDailySells(dailySellsFilter, timeOffSet);
    }

    //and this one will be used when user want to generate pdf value
    @GetMapping(path = "/daily-sells-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findDailySellsPdf(DailySellsFilter dailySellsFilter,
                             @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) throws JRException {

            byte[] pdfBytes = reportService.generateDailySellsPdf(dailySellsFilter, timeOffSet);

            var headers = new HttpHeaders(); //creating new headers

            //attachment indicates that response body 'pdfBytes' must be downloaded by client, not displayed in line as json
            //filename is default name for file
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sells.pdf");

            //customizing with responseEntity with a type,
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF) //saying this content body type will be pdf type
                    .headers(headers) //personalized headers
                    .body(pdfBytes); //in body will have byte array

    }


}
