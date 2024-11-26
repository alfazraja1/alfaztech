package com.alfaztech.authService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alfaztech.authService.model.Users;
import com.alfaztech.authService.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Users registerUser(Users user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists.");
        }

        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<Users> authenticateUser(String email, String password) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
    
    public Users updateProfile(String email, Users updatedUser) {
        // Find the user by their ID (you could also use email if you prefer)
        Users existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user details
        if(updatedUser.getFullName()!=null) {
        existingUser.setFullName(updatedUser.getFullName());
        }
        if(updatedUser.getGender()!=null) {
        existingUser.setGender(updatedUser.getGender());
        }
        if(updatedUser.getAge()!= 0) {
        existingUser.setAge(updatedUser.getAge());
        }
        if(updatedUser.getMobile()!=null) {
        existingUser.setMobile(updatedUser.getMobile());
        }
        if(updatedUser.getImageUrl()!=null)
        {
        	existingUser.setImageUrl(updatedUser.getImageUrl());
        }

        // Save the updated user back to the database
        return userRepository.save(existingUser);
    }
}
