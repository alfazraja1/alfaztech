package com.alfaztech.authService.controller;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alfaztech.authService.dto.LoginResponse;
import com.alfaztech.authService.dto.LoginUserDto;
import com.alfaztech.authService.dto.RegisterUserDto;
import com.alfaztech.authService.model.Users;
import com.alfaztech.authService.service.AuthenticationService;
import com.alfaztech.authService.service.JwtService;
import com.alfaztech.authService.service.UserService;

@RequestMapping("/auth")
@RestController

public class AuthenticationController {
    private final JwtService jwtService;
    
    private UserService userService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userService=userService;
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Users> register(@RequestBody RegisterUserDto registerUserDto) {
        Users registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Users authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
       Users user= userService.authenticateUser(loginUserDto.getEmail(), loginUserDto.getPassword()).get();

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()).setUser(user);

        return ResponseEntity.ok(loginResponse);
    }
    
   
}
