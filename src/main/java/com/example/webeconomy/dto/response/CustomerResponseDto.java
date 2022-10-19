package com.example.webeconomy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class CustomerResponseDto {

	private Long id;

	@JsonProperty("name")
	private String name;

	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}




}
