package com.example.webeconomy.services.impl;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.webeconomy.services.AccountService;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.response.AccountResponseDto;
import com.example.webeconomy.exceptions.*;


@Service
public class AccountServiceImpl implements AccountService{
    
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }
    
    @Override
    public Account getAccountById(Long id){
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }        
        Account account= accountOptional.get();
        return account;
    }
    @Override
    public AccountResponseDto createAccount(AccountUpdateDto dto){
        Account account = modelMapper.map(dto,Account.class);
        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto updateAccount(Long id, AccountUpdateDto dto){
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found");
        }
        Account account = accountOptional.get();
        modelMapper.map(dto,account);
        account = accountRepository.save(account);
        return modelMapper.map(account, AccountResponseDto.class);
    }
    @Override
    public Account getAccountByPhoneNumber(String phoneNumber){
        Optional<Account> accountOptional =  this.accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found");
        }
        Account account= accountOptional.get();
        return account;
    }

}
