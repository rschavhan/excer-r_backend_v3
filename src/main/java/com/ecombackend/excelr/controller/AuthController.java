package com.ecombackend.excelr.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecombackend.excelr.dto.LoginRequest;
import com.ecombackend.excelr.model.Role;
import com.ecombackend.excelr.model.User;
import com.ecombackend.excelr.service.RoleService;
import com.ecombackend.excelr.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        // Fetch or create the "ROLE_USER" role
        Role userRole = roleService.findByName("USER").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("USER");
            return roleService.saveRole(newRole);
        });

        signUpRequest.setRoles(Set.of(userRole));  // Assign the role to the user

        userService.saveUser(signUpRequest);

        return ResponseEntity.ok("User registered successfully with email: " + signUpRequest.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.findByUsername(loginRequest.getEmail());

        if (userOptional.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        User user = userOptional.get();
        Set<Role> roles = user.getRoles();  // Get the user's roles

        return ResponseEntity.ok("Login successful for Roles: " + roles + " | Username: " + user.getUsername());
    }
}
