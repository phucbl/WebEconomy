package com.example.webeconomy.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDto {
    @NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Address is required")
	private String address;

}
