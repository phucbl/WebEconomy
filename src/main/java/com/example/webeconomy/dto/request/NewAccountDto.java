package com.example.webeconomy.dto.request;

import lombok.Data;

@Data
public class NewAccountDto {
    private String phoneNumber;
    private String password;
    private String name;
    private String address;
}
