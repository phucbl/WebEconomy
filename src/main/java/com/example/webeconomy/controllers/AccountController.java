package com.example.webeconomy.controllers;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.response.AccountResponseDto;
import com.example.webeconomy.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;


    @Autowired
    AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    // @PostMapping("/account")
    // Accounts newAccounts (@RequestBody Accounts newAccounts) {
    //   return accountServices.save(newAccounts);
    // }

    @GetMapping
    List<Account> all(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    Account getAccountByID(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }
    @GetMapping("/")
    Account getAccountByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber){
        return accountService.getAccountByPhoneNumber(phoneNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponseDto createAccount(@RequestBody AccountUpdateDto dto){
        return accountService.createAccount(dto);
    }
    
    @PutMapping("/{id}")
    AccountResponseDto updateAccount(@PathVariable("id") Long id, @Valid @RequestBody AccountUpdateDto dto){
        return accountService.updateAccount(id,dto);
    }

    
}
