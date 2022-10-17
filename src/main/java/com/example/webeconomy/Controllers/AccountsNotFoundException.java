package com.example.webeconomy.Controllers;

public class AccountsNotFoundException extends RuntimeException{
    AccountsNotFoundException (long id){
        super("Could not find account "+id);
    }
    AccountsNotFoundException (String PhoneNumber){
        super("Could not find account "+PhoneNumber);
    }
}
