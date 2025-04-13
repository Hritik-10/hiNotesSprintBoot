package com.hritech.hinotes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hritech.hinotes.Model.User;
import com.hritech.hinotes.Repository.UserRepo;
import com.hritech.hinotes.Security.CustomUserDetails;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepo.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + name));
        return new CustomUserDetails(user);
    }
}

