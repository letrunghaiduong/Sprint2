package com.example.seafoodbe.service;

import com.example.seafoodbe.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUserName(String userName);
    void createUser (User user);
    Optional<User> findById(Integer id);
}
