package com.example.webeconomy.services;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.response.AccountResponseDto;
import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();
    public Account getAccountById(Long id);
    public AccountResponseDto getAccountByIdDto(Long id);
    public AccountResponseDto createAccount(AccountUpdateDto dto);
    public AccountResponseDto updateAccount(AccountUpdateDto dto);
    public Account getAccountByPhoneNumber (String phoneNumber);

}
