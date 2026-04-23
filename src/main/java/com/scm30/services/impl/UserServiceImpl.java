package com.scm30.services.impl;

import com.scm30.helper.ResourceNotFoundException;
import com.scm30.repositories.UserRepo;
import com.scm30.services.UserService;
import com.scm30.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

//    private Logger logger  = (Logger) LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public User updateUser(User user) {

            User user2 = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        User save = userRepo.save(user2);

        return save;
    }


    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));
         userRepo.delete(user2);

    }

    @Override
    public boolean isUserExist(String id) {
        User user2 = userRepo.findById(id).orElse(null);

        return user2 != null;
    }

    @Override
    public boolean isUserExistByEmail(String email) {

        return false;
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }
}
