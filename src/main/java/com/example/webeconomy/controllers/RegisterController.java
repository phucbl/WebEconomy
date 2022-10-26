package com.example.webeconomy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.request.NewAccountDto;
import com.example.webeconomy.dto.response.AccountResponseDto;
import com.example.webeconomy.services.AccountService;

@RestController
@RequestMapping("/customer/register")
public class RegisterController {
    
    @Autowired
    private AccountService accountService;
    @Autowired
    RegisterController (AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto createAccount (@RequestBody NewAccountDto dto){
        return accountService.createAccount(dto);
    }
}
