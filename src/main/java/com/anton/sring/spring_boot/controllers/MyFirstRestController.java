package com.anton.sring.spring_boot.controllers;

import com.anton.sring.spring_boot.models.Role;
import com.anton.sring.spring_boot.models.User;
import com.anton.sring.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sun.security.util.SecurityConstants;

import java.util.List;

public class MyFirstRestController {

    @Qualifier("userDetailService")
    @Autowired
    private UserService userService;

    @GetMapping ("/getAuthorized")
    public ResponseEntity<User> getAuthorizedUser() {
        org.springframework.security.core.userdetails.User authorizedUser=(org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userService.findByUserPassword(authorizedUser.getUsername()));
    }
    @GetMapping("/allUsers")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    public ResponseEntity<User> create (@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);

    }
    @GetMapping("/allRoles")
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok().body(userService.getAllRolesObjects());
    }

    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findbyid(id));
    }
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
