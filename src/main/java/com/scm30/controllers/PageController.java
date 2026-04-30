package com.scm30.controllers;

import com.scm30.entity.User;
import com.scm30.model.UserForm;
import com.scm30.services.UserService;
import com.scm30.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String rootRedirect() {
        return "redirect:/user/dashboard";
    }

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
    public String registerUser(@Valid @ModelAttribute("user") UserForm userForm, BindingResult rBindingResult , RedirectAttributes redirectAttributes){
//        System.out.println(user.getName());
//        System.out.println(user.getEmail());
//        System.out.println(user.getAbout());
//        System.out.println(user.getPhoneNumber());

        //Validate form data:---
        if(rBindingResult.hasErrors()){
            return "register";
        }


       User user = new User();
       user.setName(userForm.getName());
       user.setEmail(userForm.getEmail());
       user.setPassword(userForm.getPassword());
       user.setAbout(userForm.getAbout());
       user.setPhoneNumber(userForm.getPhoneNumber());
       user.setProfilePic("https://www.bing.com/images/search?view=detailV2&ccid=302zgzUH&id=866097613A47EDC880CFD7A95F7211415E0C5EEA&thid=OIP.302zgzUHVpOuGmsmRZudiAHaHk&mediaurl=https%3A%2F%2Fwallpapers.com%2Fimages%2Fhd%2Fcool-profile-picture-87h46gcobjl5e4xu.jpg&exph=1920&expw=1880&q=profile+pictures&form=IRPRST&ck=DF8E3EEE74B2367E808B4F91F0C1B8E8&selectedindex=3&itb=0&ajaxhist=0&ajaxserp=0&vt=0&sim=11");
       userService.saveUser(user);

       //add message:
        redirectAttributes.addFlashAttribute("message", "Registration Successful!");
        redirectAttributes.addFlashAttribute("messageType", "success");



        return "redirect:/signup";
    }

}
