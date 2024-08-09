package com.ecombackend.excelr.dto;

import java.util.Set;

import com.ecombackend.excelr.model.Role;

public class LoginResponse {
    private Long userId;
    private Set<Role> roles;

    public LoginResponse(Long userId, Set<Role> roles) {
        this.userId = userId;
        this.roles = roles;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
