package com.example.webeconomy.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class CustomerUpdateDto {
	private Long id;
	private Long accountId;
	private String name;
	private String address;

}
