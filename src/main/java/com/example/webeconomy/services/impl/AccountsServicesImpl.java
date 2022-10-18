package com.example.webeconomy.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webeconomy.services.AccountsServices;
import com.example.webeconomy.entities.*;
import com.example.webeconomy.repositories.*;

@Service
public class AccountsServicesImpl implements AccountsServices{
    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    public AccountsServicesImpl (AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return this.accountsRepository.findAll();
    }
    
    @Override
    public Accounts getAccountByPhoneNumber(String phoneNumber){
        return this.accountsRepository.findByPhoneNumber(phoneNumber);
    }

}
