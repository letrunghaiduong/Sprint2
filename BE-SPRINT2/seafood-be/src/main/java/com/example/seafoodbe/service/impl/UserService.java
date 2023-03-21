package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.User;
import com.example.seafoodbe.repository.IUserRepository;
import com.example.seafoodbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
}
