package com.todotask.demo.dto;

import com.todotask.demo.model.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthResponse {
    public String token;
    public User user;
    
    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
