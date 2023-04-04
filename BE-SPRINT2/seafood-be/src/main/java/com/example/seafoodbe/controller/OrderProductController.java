package com.example.seafoodbe.controller;

import com.example.seafoodbe.dto.Order;
import com.example.seafoodbe.service.IOrderDetailService;
import com.example.seafoodbe.service.IOrderProductService;
import com.example.seafoodbe.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RequestMapping("api/orderProduct")
@RestController
@CrossOrigin("*")
public class OrderProductController {
    @Autowired
    private IOrderProductService orderProductService;

    @Autowired
    private ISizeService sizeService;

    @PostMapping("/add")
    private ResponseEntity<?> addCart(@RequestBody Order order) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentDateTime.format(formatter);

        orderProductService.addOrder(formattedDateTime,order.getShippingAddress(),order.getTotalPrice(),order.getOrderDetailId());
        sizeService.update(order.getQuantity(),order.getProductId(),order.getSize());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
