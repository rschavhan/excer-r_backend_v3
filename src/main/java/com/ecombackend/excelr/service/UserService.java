package com.ecombackend.excelr.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecombackend.excelr.model.Role;
import com.ecombackend.excelr.model.User;

public interface UserService {

	boolean existsByEmail(String email);

	void saveUser(User user);

	Optional<User> findByUsername(String username); 

	List<User> getAllUsers();

	void deleteUser(Long id);

	Optional<User> findById(Long id);

	void updateRoleForUser(Long userId, Long newRoleId);


	 Optional<User> findByEmail(String email);

}
