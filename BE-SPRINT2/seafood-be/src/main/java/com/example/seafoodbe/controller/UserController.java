package com.example.seafoodbe.controller;


import com.example.seafoodbe.model.User;
import com.example.seafoodbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/user")
@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/findById")
    private ResponseEntity<?> findById(@RequestParam(defaultValue = "", required = false) Integer userId) {
        if (userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            Optional<User> user = userService.findById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

    }
}
