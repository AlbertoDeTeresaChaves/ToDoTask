package com.todotask.demo.dto;

import com.todotask.demo.model.UserDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthResponse {
    public String token;
    public UserDTO userDTO;
    
    public AuthResponse(String token, UserDTO userDTO) {
        this.token = token;
        this.userDTO = userDTO;
    }
}
