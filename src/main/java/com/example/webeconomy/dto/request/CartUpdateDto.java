package com.example.webeconomy.dto.request;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Min;

import com.example.webeconomy.data.entities.ProductCustomerId;

import lombok.Data;

@Data
public class CartUpdateDto {

	@NotBlank(message = "CustomerId and ProductID is required")
    private ProductCustomerId id;

    @Min(1)
	@NotBlank(message = "Password is required")
    private int quantity;

    private boolean check;
}
