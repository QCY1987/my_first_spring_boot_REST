package com.anton.sring.spring_boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.anton.sring.spring_boot.models.Role;
import com.anton.sring.spring_boot.models.User;
import com.anton.sring.spring_boot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {
@Qualifier("userDetailsService")
    @Autowired
    private UserService userService;
    private Role role;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView AllUsers(ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName("admin.html");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("allroles", userService.getAllRolesObjects());
        modelAndView.addObject("users", userService.findAllUsers());
        modelAndView.addObject("currentUser", userService.findByUserPassword(principal.getName()));
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName("user.html");
        modelAndView.addObject("users", userService.findAllUsers());
        modelAndView.addObject("currentUser", userService.findByUserPassword(principal.getName()));
        return modelAndView;
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String CreateUser(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/admin/createuser")
    public String newUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users/admin";
    }

    @PostMapping("/admin/update")
    public String update(@ModelAttribute("user") User user, Model model) {
        user = userService.findbyid(user);
        model.addAttribute("users", user);
        return "update";
    }

    @PostMapping("/admin/updateuser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users/admin";
    }

    @PostMapping("/admin/remove")
    public String delete(@ModelAttribute("user") User user) {
        userService.deleteById(user);
        return "redirect:/users/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accesssDenied() {
        return "403";
    }
}