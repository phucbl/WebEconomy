package com.example.webeconomy.dto.response;
import com.example.webeconomy.constants.Erole;

import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
	private String phoneNumber;
    private Erole roleId;
}
