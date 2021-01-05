package com.anton.sring.spring_boot.service;


import com.anton.sring.spring_boot.models.Role;
import com.anton.sring.spring_boot.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteById(User user);

    List<User> findAllUsers();

    void update(User user);

    User findbyid(User user);

    List<Role> findRolesById(User user);

    User findByUserLogin(String userlogin);

    List<String> getAllRoles(Role role);

    List<Role> getAllRolesObjects();

    User findByUserPassword(String name);
}
