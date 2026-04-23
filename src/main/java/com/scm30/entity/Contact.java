package com.scm30.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;
    private boolean favorite=false;
    private String webSiteLink;
    private String linkedInLink;
    //private List<SocialLink> socialLinks =  new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();

}
