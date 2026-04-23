package com.scm30.controllers;

import com.scm30.entity.User;
import com.scm30.model.UserForm;
import com.scm30.services.UserService;
import com.scm30.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {
    @Autowired
    UserService userService;


    @RequestMapping("/home")
    public String Home(Model model){
        model.addAttribute("youtubechannel","https://www.youtube.com/watch?v=SAqi7zmW1fY&t=4569s");
        model.addAttribute("name","Substring Technologies");
      return "home";
    }
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("islogin",true);
        System.out.println("About page loading!");
        return "about";
    }
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("services page loading!");
        return "service";
    }
    @RequestMapping("/base")
    public String basePage(){
        System.out.println("base page loading!");
        return "base";
    }
    @GetMapping("/login")
    public String login(){
        System.out.println("Login page loading!");
        return new String("login");
    }
    @GetMapping("/signup")
    public String register(Model model){
        System.out.println("signup page loading!");
        model.addAttribute("user",new UserForm());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserForm userForm){
//        System.out.println(user.getName());
//        System.out.println(user.getEmail());
//        System.out.println(user.getAbout());
//        System.out.println(user.getPhoneNumber());
       User user = new User();
       user.setName(userForm.getName());
       user.setEmail(userForm.getEmail());
       user.setPassword(userForm.getPassword());
       user.setAbout(userForm.getAbout());
       user.setPhoneNumber(user.getPhoneNumber());

        userService.saveUser(user);

        return "redirect:/login";
    }

}
