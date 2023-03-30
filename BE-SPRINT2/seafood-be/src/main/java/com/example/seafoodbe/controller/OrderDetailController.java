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
                                                @RequestParam(defaultValue = "", required = false) Integer quantity,
                                                @RequestParam(required = false) double size) {

        OrderDetail[] orderDetail = orderDetailService.findByProductId(productId);
        if (orderDetail.length == 0){
            orderDetailService.addNew(productId, userId,quantity ,size);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            for (int i = 0; i < orderDetail.length; i++) {
                if (orderDetail[i].getSize() == size){
                    orderDetailService.update(quantity, productId,size);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            orderDetailService.addNew(productId, userId,quantity ,size);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @GetMapping("/update")
    private ResponseEntity<?> update(@RequestParam(defaultValue = "", required = false) Integer quantity,
                                     @RequestParam(defaultValue = "", required = false) Integer productId,
                                     @RequestParam(required = false) double size) {
        orderDetailService.update(quantity, productId,size);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    private ResponseEntity<?> delete(@RequestParam(defaultValue = "", required = false) Integer id) {
        orderDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
