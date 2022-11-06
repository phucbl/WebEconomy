package com.example.webeconomy.dto.response;
import lombok.Data;

@Data
public class CustomerResponseDto {
	private Long id;
	private String phoneNumber;
	private String name;
	private String address;
	private Long accountId;
	private boolean status;
}
