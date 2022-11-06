package com.example.webeconomy.controllers;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.webeconomy.dto.request.AccountUpdateDto;
import com.example.webeconomy.dto.response.AccountResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;
import com.example.webeconomy.services.AccountService;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @GetMapping("/{id}")
    AccountResponseDto getAccountByID(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }
    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponseDto createAdminAccount(@RequestBody AccountUpdateDto dto){
        return accountService.createAdminAccount(dto);
    }
    
    @PutMapping("/{id}")
    AccountResponseDto updateAccount(@PathVariable("id") Long id, @Valid @RequestBody AccountUpdateDto dto){
        return accountService.updateAccount(id,dto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto> deactiveAccount(@PathVariable("id") Long id){
        return accountService.deactiveAccount(id);
    }
    
}
