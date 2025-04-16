package com.hritech.hinotes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hritech.hinotes.Model.User;
import com.hritech.hinotes.Repository.UserRepo;

@Service
public class CurrentUser {
    @Autowired
    private UserRepo userRepo;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
