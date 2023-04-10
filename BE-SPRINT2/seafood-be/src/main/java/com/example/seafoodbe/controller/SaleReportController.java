package com.example.seafoodbe.controller;

import com.example.seafoodbe.dto.ISalesReport;
import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.service.ISalesReportService;
import com.example.seafoodbe.service.impl.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/saleReport")
@RestController
@CrossOrigin("*")
public class SaleReportController {
    @Autowired
    private ISalesReportService salesReportService;

    @GetMapping("")
    private ResponseEntity<?> saleReport(@RequestParam(defaultValue = "", required = false) Integer userId) {
        List<ISalesReport> salesReports = salesReportService.salesReport();
        if (salesReports.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<ICart>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(salesReports, HttpStatus.OK);
    }

}
