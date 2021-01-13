package com.anton.sring.spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anton.sring.spring_boot.repository.RoleRepository;
import com.anton.sring.spring_boot.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByRole(name);
    }

}
