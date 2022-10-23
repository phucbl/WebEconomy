package com.example.webeconomy.services;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.response.AccountResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public AccountResponseDto getAccountById(Long id);
    public AccountResponseDto createAccount(AccountUpdateDto dto);
    public AccountResponseDto updateAccount(Long id, AccountUpdateDto dto);
    HttpStatus deactiveAccount(Long id);
    public AccountResponseDto getAccountByPhoneNumber (String phoneNumber);

}
