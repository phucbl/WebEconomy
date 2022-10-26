package com.example.webeconomy.dto.request;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
@Data
public class AccountUpdateDto {
	@Size(min = 10,max = 12, message = "PhoneNumber must be at least 10 characters")
	@NotBlank(message = "Phone number is required")
	private String phoneNumber;

	@Size(min = 3, message = "Password must be at least 6 characters")
	@NotBlank(message = "Password is required")
	private String password;
}
