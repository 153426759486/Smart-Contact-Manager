package com.scm30.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.security.Principal;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){

//        AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();

        if(authentication instanceof  OAuth2AuthenticationToken){
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            if(clientId.equalsIgnoreCase("google")){
                System.out.println("Google login:");
            }
            else if(clientId.equalsIgnoreCase("github")){
                System.out.println("Github login:");
            }
            return "";
        }
            else{
                System.out.println("Login with local database: ");
                return authentication.getName();
            }
        }


    }

