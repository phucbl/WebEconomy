package com.example.webeconomy.dto.request;

import lombok.Data;

@Data
public class NewAccountDto {
    private AccountUpdateDto accountDto;
    private CustomerUpdateDto customerDto;
}
