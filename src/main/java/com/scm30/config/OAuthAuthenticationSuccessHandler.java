package com.scm30.config;

import com.scm30.entity.Providers;
import com.scm30.entity.User;
import com.scm30.helper.AppConstants;
import com.scm30.repositories.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        logger.info("OAuth success handler triggered");

//        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
//
//        String email = oauthUser.getAttribute("email");
//        String name = oauthUser.getAttribute("name");
//        String picture = oauthUser.getAttribute("picture");
//
//        logger.info("Processing user: {}", email);
//
//        User userEntity = userRepo.findByEmail(email)
//                .orElseGet(() -> {
//                    User newUser = new User();
//                    newUser.setEmail(email);
//                    newUser.setProvider(Providers.GOOGLE);
//                    newUser.setEnabled(true);
//                    newUser.setEmailVerified(true);
//                    newUser.setRoleList(List.of(AppConstants.ROLE_USER));
//                    return newUser;
//                });
//
//        userEntity.setName(name);
//        userEntity.setProfilePic(picture);
//        userEntity.setProviderUserId(oauthUser.getName());
//
//        userRepo.save(userEntity);
//
//        logger.info("User saved successfully");

      var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
      String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

      logger.info(authorizedClientRegistrationId);
      var oAuth2User = (DefaultOAuth2User)authentication.getPrincipal();
      oAuth2User.getAttributes().forEach((key,value)->{
          logger.info(key +":" +value);
      });


      User user = new User();
//      user.setUserId(UUID.randomUUID().toString());

      user.setRoleList(List.of(AppConstants.ROLE_USER));
      user.setEmailVerified(true);
      user.setEnabled(true);


      if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
            user.setEmail(oAuth2User.getAttribute("email").toString());
            user.setProfilePic(oAuth2User.getAttribute("picture").toString());
            user.setName(oAuth2User.getAttribute("name").toString());
            user.setProviderUserId(oAuth2User.getName());
          user.setProvider(Providers.GOOGLE);
          user.setAbout("This is Google Login");
      }
      else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
                String email = oAuth2User.getAttribute("email") != null ?
                        oAuth2User.getAttribute("email").toString() : oAuth2User.getAttribute("login").toString()+"@gmail.com";
                String picture = oAuth2User.getAttribute("avatar_url").toString();
                String name = oAuth2User.getAttribute("login").toString();
                String providerUserId = oAuth2User.getName();

                user.setEmail(email);
                user.setProfilePic(picture);
                user.setName(name);
                user.setProviderUserId(providerUserId);
                user.setProvider(Providers.GITHUB);
                user.setAbout("This is Github Login");
      }
      User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if(user2==null){
            userRepo.save(user);
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}