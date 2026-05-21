package com.scm30.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){

//        AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();

        if(authentication instanceof  OAuth2AuthenticationToken){
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = "";

            if(clientId.equalsIgnoreCase("google")){
                System.out.println("Google login:");
                username = oauth2User.getAttribute("email").toString();
            }
            else if(clientId.equalsIgnoreCase("github")){
                System.out.println("Github login:");
                username = oauth2User.getAttribute("email") != null ?
                        oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString()+"@gmail.com";
            }
            return username;
        }
            else{
                System.out.println("Login with local database: ");
                return authentication.getName();
            }
        }


    }

