package com.hritech.hinotes.Controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hritech.hinotes.Model.User;
import com.hritech.hinotes.Repository.UserRepo;
import com.hritech.hinotes.Security.JwtUtil;
import com.hritech.hinotes.Service.CurrentUser;
import com.hritech.hinotes.dto.LoginRequest;
import com.hritech.hinotes.dto.SignupRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CurrentUser currentUser;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest() .body("User already exists");
        }
        System.out.println("in signup");
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        String token = jwtUtil.generateToken(request.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("auth-token", token));
        // return ResponseEntity.ok("User registered successfully/n"+user.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("in login");
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("authToken", token));
    }

    @GetMapping("/userdetails")
    public ResponseEntity<?> getUserDetails() {
        User user = currentUser.getCurrentUser();

        return ResponseEntity.ok(Collections.singletonMap("currentUser: ", user));
    }

}

