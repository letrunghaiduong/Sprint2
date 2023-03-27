package com.example.seafoodbe.controller;

import com.example.seafoodbe.model.Image;
import com.example.seafoodbe.model.Size;
import com.example.seafoodbe.service.impl.ImageService;
import com.example.seafoodbe.service.impl.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/size")
@RestController
@CrossOrigin("*")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/list")
    private ResponseEntity<?> showList(@RequestParam(defaultValue = "", required = false) Integer productId) {
        if (productId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            List<Size> imageList = sizeService.findByIdProduct(productId);
            return new ResponseEntity<>(imageList, HttpStatus.OK);
        }

    }
}
