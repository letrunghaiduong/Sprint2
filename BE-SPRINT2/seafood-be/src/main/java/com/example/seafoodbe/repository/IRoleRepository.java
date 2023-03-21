package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.Role;
import com.example.seafoodbe.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(RoleName roleName);
}
