package com.scm30.controllers;

import com.scm30.entity.User;
import com.scm30.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        String email = principal.getName();
        User user = userRepo.findByEmail(email).get();
        model.addAttribute("user", user);
        return "user/dashboard";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model , Principal principal){
        String email = principal.getName();
        User user = userRepo.findByEmail(email).get();


        model.addAttribute("user", user);
        return "user/profile";
    }
}

