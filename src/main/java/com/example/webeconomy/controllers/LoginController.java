package com.example.webeconomy.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webeconomy.dto.request.LoginInputDto;
import com.example.webeconomy.dto.response.LoginResponseDto;
import com.example.webeconomy.services.AuthService;

@RestController
@RequestMapping("/customer/login")
public class LoginController {
    @Autowired
    private AuthService authService;
    @Autowired
    LoginController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping
    public LoginResponseDto login(@RequestBody LoginInputDto dto) {
    return authService.login(dto);
    }

}
