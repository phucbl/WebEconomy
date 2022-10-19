package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class BookResponseDto {
	private Long id;

	@JsonProperty("name")
	private String bookName;
//	private String author;
//	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

//	public String getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//
//	public Double getPrice() {
//		return price;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}

}
