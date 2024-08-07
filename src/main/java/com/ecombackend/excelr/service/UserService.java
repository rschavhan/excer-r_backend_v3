package com.ecombackend.excelr.service;

import java.util.List;
import java.util.Optional;

import com.ecombackend.excelr.model.User;

public interface UserService {

    boolean existsByEmail(String email);

    void saveUser(User user);

    Optional<User> findByUsername(String username);
    List<User> getAllUsers();

	void deleteUser(Long id);
}
