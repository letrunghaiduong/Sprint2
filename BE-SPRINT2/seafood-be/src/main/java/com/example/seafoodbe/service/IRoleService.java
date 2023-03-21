package com.example.seafoodbe.service;

import com.example.seafoodbe.model.Role;
import com.example.seafoodbe.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleName);
}
