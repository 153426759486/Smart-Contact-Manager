package com.scm30.services.impl;

import com.scm30.entity.Contact;
import com.scm30.entity.User;
import com.scm30.helper.ResourceNotFoundException;
import com.scm30.repositories.ContactRepo;
import com.scm30.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl  implements ContactService {
    @Autowired ContactRepo contactRepo;
    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Contact not Foundwith given id!" +id));
    }

    @Override
    public void delete(String id) {
    var contact = contactRepo.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Contact not Foundwith given id!" +id));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }



    @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction){

        Sort sort = direction.equals("desc")? Sort.by(sortBy):Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size, sort);
        return contactRepo.findByUser(user,pageable);
    }
}
