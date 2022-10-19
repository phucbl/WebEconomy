package com.example.webeconomy.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductUpdateDto {
	@NotBlank(message = "Book name is required")
	private String name;
	private String description;
	@Min(value = 1, message = "Price should be a positive number")
	private Double price;
	private String Origin;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
