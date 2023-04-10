package com.example.seafoodbe.repository;

import com.example.seafoodbe.dto.ISalesReport;
import com.example.seafoodbe.model.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISalesReportRepository extends JpaRepository<SalesReport,Integer> {


    @Query(value = "select p.name as name, sum(op.total_price) as revenues\n" +
            "from order_product as op\n" +
            "join order_detail od on od.id = op.order_detail_id\n" +
            "join product p on p.id = od.product_id\n" +
            "group by name", nativeQuery = true)
    List<ISalesReport> salesReport();
}
