package com.scm30.services;

import com.scm30.entity.Contact;
import com.scm30.entity.User;
import org.springframework.data.domain.Page;
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


    //get contact by userId

    List<Contact> getByUserId(String userId);


    //search contact
    Page<Contact> searchUserByName(String nameKeyword, int size, int page,String sortBy, String order);

    Page<Contact> searchUserByEmail(String emailKeyword, int size, int page,String sortBy, String order);

    Page<Contact> searchUserByPhoneNumber(String phoneNumberKeyword, int size, int page,String sortBy, String order);


    Page<Contact> getByUser(User user, int page, int size, String sortFeild, String sortDirection);
}
