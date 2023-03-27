package com.example.seafoodbe.controller;

import com.example.seafoodbe.model.Image;
import com.example.seafoodbe.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/image")
@RestController
@CrossOrigin("*")
public class IImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/list")
    private ResponseEntity<?> showList(@RequestParam(defaultValue = "", required = false) Integer productId) {
        if (productId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            List<Image> imageList = imageService.findByIdProduct(productId);
            return new ResponseEntity<>(imageList, HttpStatus.OK);
        }
    }
}
