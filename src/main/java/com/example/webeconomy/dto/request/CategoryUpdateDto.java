package com.example.webeconomy.dto.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class CategoryUpdateDto{
    @NotBlank(message = "Name Cannot Empty")
    @JsonProperty("name")
    private String name;
}