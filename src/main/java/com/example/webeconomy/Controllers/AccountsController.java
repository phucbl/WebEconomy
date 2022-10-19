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
import com.example.webeconomy.services.AccountsServices;

@RestController
@RequestMapping("/api")
public class AccountsController {
    private AccountsServices accountsServices;


    @Autowired
    AccountsController(AccountsServices accountsServices){
        this.accountsServices=accountsServices;
    }

    // @PostMapping("/account")
    // Accounts newAccounts (@RequestBody Accounts newAccounts) {
    //   return accountsServices.save(newAccounts);
    // }

    @GetMapping("/findAllAccounts")
    List<Accounts> all(){
        return accountsServices.getAllAccounts();
    }

    // @GetMapping("/account/{id}")
    // Accounts getAccountByID(@RequestParam("id") Long id){
    //     return accountsrepository.findById(id)
    //     .orElseThrow(() -> new AccountsNotFoundException(id));
    // }

    @GetMapping("/account")
    Accounts getAccountByPhonenumber(@RequestParam String phoneNumber ){
        return accountsServices.getAccountByPhoneNumber(phoneNumber);
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
