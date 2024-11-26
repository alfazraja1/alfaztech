package com.alfaztech.authService.dto;

import com.alfaztech.authService.model.Users;

public class LoginResponse {
	
	private String token;

    private long expiresIn;
    
    private Users user;

	public String getToken() {
		return token;
	}

	public LoginResponse setToken(String token) {
		this.token = token;
		return this;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public LoginResponse setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	public Users getUser() {
		return user;
	}

	public LoginResponse setUser(Users user) {
		this.user = user;
		return this;
	}
    

}
