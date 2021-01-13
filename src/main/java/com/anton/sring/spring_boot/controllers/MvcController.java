package com.anton.sring.spring_boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.anton.sring.spring_boot.service.RoleServiceImpl;
import com.anton.sring.spring_boot.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class MvcController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public MvcController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model, Principal principal) {
        model.addAttribute("autUser", userService.getUserByEmail(principal.getName()));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "index";
    }

    @GetMapping
    public String redirectToIndexPage() {
        return "redirect:/index";
    }
}
