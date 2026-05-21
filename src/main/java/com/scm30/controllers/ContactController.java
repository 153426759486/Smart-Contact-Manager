package com.scm30.controllers;

import com.scm30.entity.Contact;
import com.scm30.entity.User;
import com.scm30.helper.Helper;
import com.scm30.model.ContactForm;
import com.scm30.services.ContactService;
import com.scm30.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;

@Controller
@RequestMapping("/user/contact")
public class ContactController {
    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);
//        contactForm.setName("Aman gaur");
//        contactForm.setEmail("amangaur123@gmail.com");
//        contactForm.setPhoneNumber("698569856");
//        contactForm.setAddress("hbshdvbshvbwbsfvhjsfj");
//        contactForm.setDescription("sbvhbshvhhsvhh");
//        contactForm.setWebsiteLink("http://amancoder.com/");
//        contactForm.setLinkedinLink("https://amangaurLinkedin.com/");
//        contactForm.setFavorite(true);

        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session){
//        System.out.println(contactForm);
//        System.out.println("Name = " + contactForm.getName());
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        if (result.hasErrors()) {
            session.setAttribute("message", "Please fix the form errors");
            session.setAttribute("messageType", "red");
            return "user/add_contact";
        }
//       <-------------------------set contactForm data to contact entity---------------------->
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setFavorite(contactForm.isFavorite());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setDescription(contactForm.getDescription());
        contact.setAddress(contactForm.getAddress());
        contact.setLinkedInLink(contactForm.getLinkedinLink());
        contact.setWebSiteLink(contactForm.getWebsiteLink());
        contact.setUser(user);
        contactService.save(contact);


        session.setAttribute("message", "Contact added successfully");
        session.setAttribute("messageType", "green");
        return "redirect:/user/contact/add";
    }
}
