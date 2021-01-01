package com.anton.sring.spring_boot.controllers;


import com.anton.sring.spring_boot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String getHomePage() {
            return "home";
        }

    @GetMapping("/welcome")
    public String forOtherRoles() {
            return "welcome";
        }

    @GetMapping("/user")
    public String clickMe(Model model, Principal principal) {
        model.addAttribute("oneUser", userService.getUserByEmail(principal.getName()));
        return "show-me";
    }
    }

