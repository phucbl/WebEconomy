package com.example.webeconomy.services.impl;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    // @Override
    // public List<Account> getAllAccounts() {
    //     return accountRepository.findAll();
    // }
    
    @Override
    public AccountResponseDto getAccountById(Long id){
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }        
        Account account= accountOptional.get();
        return modelMapper.map(account, AccountResponseDto.class);
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
    public AccountResponseDto getAccountByPhoneNumber(String phoneNumber){
        Optional<Account> accountOptional =  this.accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found");
        }
        Account account= accountOptional.get();
        return modelMapper.map(account, AccountResponseDto.class);
    }

    @Override
    public HttpStatus deactiveAccount (Long id){
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found");
        }
        Account account = accountOptional.get();
        account.setStatus(false);
        account = accountRepository.save(account);
        return HttpStatus.ACCEPTED;
    }

}
