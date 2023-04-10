package com.example.seafoodbe.controller;

import com.example.seafoodbe.model.Category;
import com.example.seafoodbe.model.Origin;
import com.example.seafoodbe.service.ICategoryService;
import com.example.seafoodbe.service.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/origin")
@RestController
@CrossOrigin("*")
public class OriginController {
    @Autowired
    private IOriginService originService;

    @GetMapping("")
    private ResponseEntity<?> showList() {
        List<Origin> originList = originService.getAll();
        return new ResponseEntity<>(originList, HttpStatus.OK);
    }
}
