package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.Role;
import com.example.seafoodbe.model.RoleName;
import com.example.seafoodbe.repository.IRoleRepository;
import com.example.seafoodbe.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }


}
