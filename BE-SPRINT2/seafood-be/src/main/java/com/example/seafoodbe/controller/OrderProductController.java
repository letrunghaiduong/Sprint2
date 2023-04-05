package com.example.seafoodbe.controller;

import com.example.seafoodbe.dto.Order;
import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.IOrderProduct;
import com.example.seafoodbe.model.IPurchaseHistory;
import com.example.seafoodbe.model.OrderProduct;
import com.example.seafoodbe.service.IOrderDetailService;
import com.example.seafoodbe.service.IOrderProductService;
import com.example.seafoodbe.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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

        orderProductService.addOrder(formattedDateTime,order.getShippingAddress(),order.getTotalPrice(),order.getOrderDetailId(),order.getCode());
        sizeService.update(order.getQuantity(),order.getProductId(),order.getSize());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/detailPurchase")
    private ResponseEntity<?> detailPurchase(@RequestParam(defaultValue = "", required = false) String code) {
        List<IPurchaseHistory> purchaseHistories = orderProductService.detailPurchase(code);
        if (purchaseHistories.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<IPurchaseHistory>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(purchaseHistories, HttpStatus.OK);
    }

    @GetMapping("/list")
    private ResponseEntity<?> getAll(@RequestParam(defaultValue = "", required = false) Integer userId) {
        List<IOrderProduct> purchaseHistories = orderProductService.getAllOrder(userId);
        if (purchaseHistories.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<IOrderProduct>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(purchaseHistories, HttpStatus.OK);
    }
}
