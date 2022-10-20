package com.example.webeconomy.dto.request;

import lombok.Data;
@Data
public class AccountUpdateDto {
	private String phoneNumber;
	private String password;
	private int roleId;
}
