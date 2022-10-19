package com.example.webeconomy.services;
import com.example.webeconomy.data.entities.*;

import java.util.List;

public interface AccountsServices {
    public List<Accounts> getAllAccounts();

    public Accounts getAccountByPhoneNumber (String phoneNumber);

}
