package com.example.seafoodbe.controller;

import com.example.seafoodbe.model.*;
import com.example.seafoodbe.service.IProductService;
import com.example.seafoodbe.service.ISizeService;
import com.example.seafoodbe.service.impl.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("api/product")
@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;


    @Autowired
    private ISizeService sizeService;

    @GetMapping("/list")
    private ResponseEntity<?> getAll(@RequestParam(defaultValue = "", required = false) String search,
                                     @PageableDefault(size = 4) Pageable pageable) {
        Page<IProduct> productList = productService.showList(search, pageable);
        if (productList.isEmpty()){
            return new ResponseEntity<>(new ArrayList<IProduct>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @GetMapping("/sellingProducts")
    private ResponseEntity<?> getAll() {
        List<ISellingProducts> sellingProducts = productService.sellingProducts();
        if (sellingProducts.isEmpty()){
            return new ResponseEntity<>(new ArrayList<IProduct>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(sellingProducts, HttpStatus.OK);
    }



    @GetMapping("/findById")
    private ResponseEntity<?> findById(@RequestParam(defaultValue = "", required = false) Integer productId) {
        if (productId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            Optional<Product> product = productService.findById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }


    @PostMapping("/add")
    private ResponseEntity<?> addSupplier(@RequestBody Product product ) {
        productService.addProduct(product.getName(),product.getPrice(),product.getCategory().getId(),product.getOrigin().getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    private ResponseEntity<?> getAllProduct(@PageableDefault(size = 4) Pageable pageable) {
        Page<Product> productList = productService.getAll(pageable);
        if (productList.isEmpty()){
            return new ResponseEntity<>(new ArrayList<IProduct>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


}
