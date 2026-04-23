package com.scm30.model;

//import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class UserForm {
    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserForm(String name, String email, String password, String about, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.phoneNumber = phoneNumber;
    }

    public UserForm() {
    }
}


