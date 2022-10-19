package com.example.webeconomy.controllers;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.services.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {
    private AccountService accountServices;


    @Autowired
    AccountController(AccountService accountServices){
        this.accountServices=accountServices;
    }

    // @PostMapping("/account")
    // Accounts newAccounts (@RequestBody Accounts newAccounts) {
    //   return accountServices.save(newAccounts);
    // }

    @GetMapping("/findAllAccounts")
    List<Account> all(){
        return accountServices.getAllAccounts();
    }

    // @GetMapping("/account/{id}")
    // Accounts getAccountByID(@RequestParam("id") Long id){
    //     return accountsrepository.findById(id)
    //     .orElseThrow(() -> new AccountsNotFoundException(id));
    // }

    @GetMapping("/account")
    Account getAccountByPhonenumber(@RequestParam String phoneNumber ){
        return accountServices.getAccountByPhoneNumber(phoneNumber);
        // .orElseThrow(() -> new AccountsNotFoundException(PhoneNumber));
    }
    
    // @PutMapping("/account/{id}")
    // Accounts replaceAccounts(@RequestBody Accounts newAccounts, @PathVariable long id){
    //     return accountsrepository.findById(id)
    //         .map(accounts -> {
    //         accounts.setPhoneNumber(newAccounts.getPhoneNumber());
    //         accounts.setPassword(newAccounts.getPassword());
    //         accounts.setRoleId(0);
    //         return accountsrepository.save(accounts);
    //     })
    //     .orElseGet(() ->{
    //         newAccounts.setId(id);
    //         return accountsrepository.save(newAccounts);

    //     });
    // }

    // @DeleteMapping("/accout/{id}")
    // void deleteAccounts(@PathVariable Long id) {
    //     accountsrepository.deleteById(id);
//   }
}
