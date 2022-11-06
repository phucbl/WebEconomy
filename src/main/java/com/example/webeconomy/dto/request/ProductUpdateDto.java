package com.example.webeconomy.dto.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductUpdateDto {
	
	private int categoryId;
	private String name;
	private String description;
	@Min(value = 1, message = "Price should be a positive number")
	private Double price;
	private String origin;
	String imageUrl;


	
}
