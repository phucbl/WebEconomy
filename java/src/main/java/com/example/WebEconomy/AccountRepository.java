package com.example.WebEconomy;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Accounts, String> {

    List<Accounts> findByRoleId(int roleId);
      
    Accounts findByPhoneNumber (String phoneNumber);
}
