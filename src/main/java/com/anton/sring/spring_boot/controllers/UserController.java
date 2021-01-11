package com.anton.sring.spring_boot.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping ()
public class UserController {
    @GetMapping(value = "/")
    public String admin() {
        return "admin";

    }
    @GetMapping(value = "/user")
    public String user() {
        return "user";
    }

}