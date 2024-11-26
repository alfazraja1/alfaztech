package com.alfaztech.authService.controller;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alfaztech.authService.model.Users;
import com.alfaztech.authService.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	
	 @PutMapping("/update")
	 @CrossOrigin(origins = "http://localhost:5173")
	    public ResponseEntity<Users> updateProfile(
	            @AuthenticationPrincipal UserDetails userPrincipal, // Get the current authenticated user
	            @RequestBody Users updatedUser) {

	        // Get the authenticated user's ID (this can be done using JWT token, e.g., userPrincipal.getId())
	        String userEmail = ((Users) userPrincipal).getEmail();

	        // Call the service to update the user profile
	        Users updatedProfile = userService.updateProfile(userEmail, updatedUser);

	        // Return the updated profile in the response
	        return ResponseEntity.ok(updatedProfile);
	    }

}
