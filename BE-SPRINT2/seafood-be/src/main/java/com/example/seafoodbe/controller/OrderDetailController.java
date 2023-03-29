package com.example.seafoodbe.controller;

import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.OrderDetail;
import com.example.seafoodbe.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/orderDetail")
@RestController
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("/list")
    private ResponseEntity<?> getAll(@RequestParam(defaultValue = "", required = false) Integer userId) {
        List<ICart> carts = orderDetailService.getAll(userId);
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/add")
    private ResponseEntity<OrderDetail> addCart(@RequestParam(defaultValue = "", required = false) Integer productId,
                                                @RequestParam(defaultValue = "", required = false) Integer userId,
                                                @RequestParam(required = false) double size) {
        OrderDetail orderDetail = orderDetailService.findByProductId(productId);
        if (orderDetail==null){
            orderDetailService.addNew(productId, userId, size);
        }else {
            orderDetailService.update(size, productId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
