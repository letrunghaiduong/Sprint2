package com.example.seafoodbe.controller;

import com.example.seafoodbe.model.Category;
import com.example.seafoodbe.model.Image;
import com.example.seafoodbe.service.ICategoryService;
import com.example.seafoodbe.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/category")
@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    private ResponseEntity<?> showList() {
            List<Category> categoryList = categoryService.getAll();
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
