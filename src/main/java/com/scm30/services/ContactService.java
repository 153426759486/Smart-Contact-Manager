package com.scm30.services;

import com.scm30.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContactService {
    //save contact
    Contact save(Contact contact);

    //update contact
    Contact update(Contact contact);

    //get all contact
    List<Contact> getAll();

    //get By id
    Contact getById(String id);

    //delete
    void delete(String id);
    //search contact
    List<Contact> search(String name,String email,String phoneNumber);

    //get contact by userId

    List<Contact> getByUserId(String userId);
}
