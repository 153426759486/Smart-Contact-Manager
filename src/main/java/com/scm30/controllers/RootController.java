package com.scm30.controllers;


import com.scm30.entity.User;
import com.scm30.helper.Helper;

import com.scm30.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserServiceImpl userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){
        if(authentication==null){
            return;
        }
        System.out.println("Adding logged in user to the model");
        String userName =  Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User name: {}" , userName);
        User user = userService.getUserByEmail(userName);
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser" , user);
    }
}
