package com.example.webeconomy.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webeconomy.services.AccountService;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.data.repositories.*;

@Service
public class AccountServiceImpl implements AccountService{
    
    private AccountRepository accountsRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl (AccountRepository accountsRepository){
        this.accountsRepository = accountsRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountsRepository.findAll();
    }
    
    @Override
    public Account getAccountByPhoneNumber(String phoneNumber){
        return this.accountsRepository.findByPhoneNumber(phoneNumber);
    }

}
