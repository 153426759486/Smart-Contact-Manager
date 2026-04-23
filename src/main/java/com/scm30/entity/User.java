package com.scm30.entity;

import jakarta.persistence.*;

//import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity (name = "user")
@Table (name="users")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @Column(name="user_name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(length = 500)
    private String profilePic;
    private String phoneNumber;

    //information
    private boolean enabled=false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // SELF,GOOGLE,FACEBOOK,TWITTER,LINKDIN,GITHUB;
    private Providers provider= Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contact> contact = new ArrayList<>();




    /// Getters and Setters

    public String getAbout() {
        return about;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public Providers getProvider() {
        return provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public List<Contact> getContact() {
        return contact;
    }



    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }



    public User(List<SocialLink> links, List<Contact> contact, String providerUserId, Providers provider, boolean phoneVerified, boolean emailVerified, boolean enabled, String phoneNumber, String profilePic, String about, String password, String email, String name, String userId) {

        this.contact = contact;
        this.providerUserId = providerUserId;
        this.provider = provider;
        this.phoneVerified = phoneVerified;
        this.emailVerified = emailVerified;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.profilePic = profilePic;
        this.about = about;
        this.password = password;
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

    public User() {
    }
}
