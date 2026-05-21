package com.scm30.controllers;

import com.scm30.entity.User;
import com.scm30.helper.Helper;
import com.scm30.repositories.UserRepo;
import com.scm30.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserServiceImpl userService;





    @RequestMapping("/dashboard")
    public String dashboard(){

//        String email = principal.getName();
//
//        User user = userRepo.findByEmail(email)
//                .orElseGet(() -> {
//                    User newUser = new User();
//                    newUser.setEmail(email);
//                    newUser.setName("Google User"); // temp
//                    newUser.setEnabled(true);
//                    return userRepo.save(newUser);
//                });
//
//        model.addAttribute("user", user);
        return "user/dashboard";
    }


    @RequestMapping("/profile")
    public String userProfile(Model model,Authentication authentication){



        return "user/profile";
    }
}

