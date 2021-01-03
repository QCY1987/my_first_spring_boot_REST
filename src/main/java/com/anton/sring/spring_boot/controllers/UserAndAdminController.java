package com.anton.sring.spring_boot.controllers;

import com.anton.sring.spring_boot.service.RoleServiceImpl;
import com.anton.sring.spring_boot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserAndAdminController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    public String homePage() {
        return "redirect:/login";
    }





}
