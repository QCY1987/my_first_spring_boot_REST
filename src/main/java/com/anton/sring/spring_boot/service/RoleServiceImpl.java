package com.anton.sring.spring_boot.service;


import com.anton.sring.spring_boot.dao.RoleRepository;
import com.anton.sring.spring_boot.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class RoleServiceImpl {
    @Autowired
    private RoleRepository roleRepository;


    public Role getRoleByName(String name) {
        return roleRepository.findByRole(name);
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}
