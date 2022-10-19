package com.example.webeconomy.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateDto {
    @NotBlank(message = "Name is required")
	private String phoneNumber;
	@NotBlank(message = "Address is required")
	private String password;

    
}
