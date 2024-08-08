package com.ecombackend.excelr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecombackend.excelr.model.User;
import com.ecombackend.excelr.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class AdminUserInitializer {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        String adminEmail = "admin@gmail.com";
        String adminPassword = "admin";
        String adminRole = "admin";
        String adminFirstName = "Ritesh";
        String adminLastName = "Chavhan";
        String adminMobileNumber = "1234567890";
        String adminUsername = "admin";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(adminRole);
            admin.setFirstName(adminFirstName);
            admin.setLastName(adminLastName);
            admin.setMobileNumber(adminMobileNumber);
            admin.setUsername(adminUsername);

            userRepository.save(admin); 
        }
    }
}
