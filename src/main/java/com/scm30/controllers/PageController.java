package com.scm30.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {
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

}
