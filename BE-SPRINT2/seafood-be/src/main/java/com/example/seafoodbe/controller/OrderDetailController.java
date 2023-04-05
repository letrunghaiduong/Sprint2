package com.example.seafoodbe.controller;

import com.example.seafoodbe.dto.OrderDetailDto;
import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.IProduct;
import com.example.seafoodbe.model.OrderDetail;
import com.example.seafoodbe.model.Size;
import com.example.seafoodbe.service.IOrderDetailService;
import com.example.seafoodbe.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/orderDetail")
@RestController
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private ISizeService sizeService;

    @GetMapping("/list")
    private ResponseEntity<?> getAll(@RequestParam(defaultValue = "", required = false) Integer userId) {
        List<ICart> carts = orderDetailService.getAll(userId);
        if (carts.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<ICart>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }


    @PostMapping("/add")
    private ResponseEntity<?> addCart(@RequestBody OrderDetailDto orderDetailDto) {
        OrderDetail[] orderDetails = orderDetailService.findByProductId(orderDetailDto.getProductId(), orderDetailDto.getUserId());
        Size size = sizeService.findQuantity(orderDetailDto.getProductId(),orderDetailDto.getSize());
            if (orderDetailDto.getUserId()==null){
                return new ResponseEntity<>("errorLogin",HttpStatus.BAD_REQUEST);
            }
            if (orderDetails.length == 0) {
                if (orderDetailDto.getQuantity() > size.getQuantity()){
                    return new ResponseEntity<>("errorQuantity",HttpStatus.BAD_REQUEST);
                }
                orderDetailService.addNew(orderDetailDto.getProductId(), orderDetailDto.getUserId(), orderDetailDto.getQuantity(), orderDetailDto.getSize());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                for (int i = 0; i < orderDetails.length; i++) {
                    if (orderDetails[i].getSize() == orderDetailDto.getSize() && !orderDetails[i].isFlagDelete()) {
                        if ((orderDetailDto.getQuantity() +orderDetails[i].getQuantity()) > size.getQuantity()){
                            return new ResponseEntity<>("errorQuantity",HttpStatus.BAD_REQUEST);
                        }
                        orderDetailService.update(orderDetailDto.getQuantity(), orderDetailDto.getProductId(), orderDetailDto.getSize(), orderDetailDto.getUserId());
                        return new ResponseEntity<>(HttpStatus.OK);
                    }
                }
                orderDetailService.addNew(orderDetailDto.getProductId(), orderDetailDto.getUserId(), orderDetailDto.getQuantity(), orderDetailDto.getSize());
                return new ResponseEntity<>(HttpStatus.OK);
            }

    }

    @PutMapping("/update")
    private ResponseEntity<?> update(@RequestBody OrderDetailDto orderDetailDto) {
        Size size = sizeService.findQuantity(orderDetailDto.getProductId(),orderDetailDto.getSize());
        OrderDetail orderDetail = orderDetailService.findOrderDetail(orderDetailDto.getProductId(),orderDetailDto.getUserId(),orderDetailDto.getSize());
        if (orderDetailDto.getQuantity() + orderDetail.getQuantity() > size.getQuantity()){
            return new ResponseEntity<>("errorQuantity",HttpStatus.BAD_REQUEST);
        }else {
            orderDetailService.update(orderDetailDto.getQuantity(), orderDetailDto.getProductId(), orderDetailDto.getSize(), orderDetailDto.getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @PutMapping("/inputQuantity")
    private ResponseEntity<?> inputQuantity(@RequestBody OrderDetailDto orderDetailDto) {
        Size size = sizeService.findQuantity(orderDetailDto.getProductId(),orderDetailDto.getSize());
        if (orderDetailDto.getQuantity() > size.getQuantity()){
            return new ResponseEntity<>("errorMaxQuantity",HttpStatus.BAD_REQUEST);
        }else if (orderDetailDto.getQuantity() < 1){
            return new ResponseEntity<>("errorMinQuantity",HttpStatus.BAD_REQUEST);
        } else {
            orderDetailService.updateQuantity(orderDetailDto.getQuantity(), orderDetailDto.getProductId(), orderDetailDto.getSize(), orderDetailDto.getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @DeleteMapping("/delete")
    private ResponseEntity<?> delete(@RequestParam(defaultValue = "", required = false) Integer id) {
        orderDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/setFlagDelete")
    private ResponseEntity<?> setFlagDelete(@RequestParam(defaultValue = "", required = false) Integer userId) {
        orderDetailService.setFlagDelete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
