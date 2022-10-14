package com.example.WebEconomy.Controllers;
import java.util.List;
import com.example.WebEconomy.Entities.*;
import com.example.WebEconomy.Repository.*;

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

import com.example.WebEconomy.Repository.AccountsRepository;

@RestController
@RequestMapping("/api")
public class AccountsController {
    @Autowired
    private AccountsRepository accountsrepository;
    @Autowired
    private CustomersRepository customersRepository;

    AccountsController(AccountsRepository accountsrepository){
        this.accountsrepository=accountsrepository;
    }

    @PostMapping("/account")
    Accounts newAccounts (@RequestBody Accounts newAccounts) {
      return accountsrepository.save(newAccounts);
    }

    @GetMapping("/findAllAccounts")
    List<Accounts> all(){
        return accountsrepository.findAll();
    }

    // @GetMapping("/account/{id}")
    // Accounts getAccountByID(@RequestParam("id") Long id){
    //     return accountsrepository.findById(id)
    //     .orElseThrow(() -> new AccountsNotFoundException(id));
    // }

    @GetMapping("/account")
    Accounts getAccountByPhonenumber(@RequestParam String phonenumber ){
        return accountsrepository.findByPhonenumber(phonenumber);
        // .orElseThrow(() -> new AccountsNotFoundException(PhoneNumber));
    }
    
    @PutMapping("/account/{id}")
    Accounts replaceAccounts(@RequestBody Accounts newAccounts, @PathVariable long id){
        return accountsrepository.findById(id)
            .map(accounts -> {
            accounts.setPhonenumber(newAccounts.getPhonenumber());
            accounts.setPassword(newAccounts.getPassword());
            accounts.setRoleId(0);
            return accountsrepository.save(accounts);
        })
        .orElseGet(() ->{
            newAccounts.setId(id);
            return accountsrepository.save(newAccounts);

        });
    }

    @DeleteMapping("/accout/{id}")
    void deleteAccounts(@PathVariable Long id) {
        accountsrepository.deleteById(id);
  }
}
