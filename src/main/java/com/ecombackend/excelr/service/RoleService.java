package com.ecombackend.excelr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecombackend.excelr.model.Role;
import com.ecombackend.excelr.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
