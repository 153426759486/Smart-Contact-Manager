package com.scm30.controllers;

import com.scm30.entity.Contact;
import com.scm30.entity.User;
import com.scm30.helper.AppConstants;
import com.scm30.helper.Helper;
import com.scm30.model.ContactForm;
import com.scm30.services.ContactService;
import com.scm30.services.ImageService;
import com.scm30.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @RequestMapping("/add")
    public String addContactView(Model model, HttpSession session){


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
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, RedirectAttributes redirectAttributes){

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

//        Process image
//        logger.info("file information: {}", contactForm.getcontactImage().getOriginalFilename());

        String fileName = UUID.randomUUID().toString();
        String fileURL = imageService.uploadImage(contactForm.getcontactImage(), fileName);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error-> logger.info(error.toString()));
//            session.setAttribute("message", "Please fix the form errors");
//            session.setAttribute("messageType", "red");
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
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(fileName);
        contact.setUser(user);
        contactService.save(contact);


//        session.setAttribute("message", "Contact added successfully");
//        session.setAttribute("messageType", "green");

        //add message:
        redirectAttributes.addFlashAttribute("message", "Contact added successfully!");
        redirectAttributes.addFlashAttribute("messageType", "success");



        return "redirect:/user/contact/add";
    }

    @RequestMapping()
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
            ,Model model,Authentication authentication){
        //get list of all contacts!

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Page<Contact> pageContact = contactService.getByUser(user,page,size,sortBy,direction);
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.pageSize);
        return "/user/contacts";
    }
}
