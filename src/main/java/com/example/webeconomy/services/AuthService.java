package com.example.webeconomy.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.webeconomy.dto.request.*;
import com.example.webeconomy.dto.response.*;

public interface AuthService extends UserDetailsService {
    UserDetails loadUserByUsername(String phoneNumber);

    ResponseEntity<ResponseDto> login(LoginInputDto loginRequest);
}
