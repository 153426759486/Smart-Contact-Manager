package com.scm30.model;

//import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class UserForm {
    @NotBlank(message = "User Name is required")
    @Size(min = 3, message = "Min 3 Character is required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(message = "Min 6 character is required" , min = 6)
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min = 8, max = 12, message = "Invalid phone number")
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


