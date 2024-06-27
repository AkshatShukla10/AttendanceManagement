package com.attendance.management.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.attendance.management.dto.LoginRequest;
import com.attendance.management.dto.RegisterRequest;
import com.attendance.management.entities.User;
import com.attendance.management.jwtUtilServices.JwtUtils;
import com.attendance.management.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            String jwt = jwtUtils.generateToken(loginRequest.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);

            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("Error: Username not found");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Error: Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: An unexpected error occurred");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(User.Role.STAFF);

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}
