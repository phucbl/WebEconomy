package com.example.webeconomy.mapper;

import com.example.webeconomy.data.entities.Account;
import com.example.webeconomy.dto.response.AccountResponseDto;

public class AccountMapper {
    public AccountResponseDto mapEntityToDto(Account account){
        return AccountResponseDto.builder()
        .id(account.getId())
        .phoneNumber(account.getPhoneNumber())
        .password(account.getPassword())
        .build();
    }
}
