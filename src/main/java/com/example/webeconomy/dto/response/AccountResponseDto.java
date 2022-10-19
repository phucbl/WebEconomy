package com.example.webeconomy.dto.response;
import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
	private String phoneNumber;
	private String password;
    private int roleId;
}
