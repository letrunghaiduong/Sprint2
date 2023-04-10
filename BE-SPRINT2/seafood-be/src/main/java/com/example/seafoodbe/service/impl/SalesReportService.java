package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.dto.ISalesReport;
import com.example.seafoodbe.repository.ISalesReportRepository;
import com.example.seafoodbe.service.ISalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesReportService implements ISalesReportService {
    @Autowired
    private ISalesReportRepository salesReportRepository;


    @Override
    public List<ISalesReport> salesReport() {
        return salesReportRepository.salesReport();
    }
}
