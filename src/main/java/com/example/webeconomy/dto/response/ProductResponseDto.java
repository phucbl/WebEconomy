package com.example.webeconomy.dto.response;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductResponseDto {

    private long id;
    private String name;
    private String descripstion;
    private String origin;
    private float price;
    private String imageUrl;
    private int count;
    private int categoryId;
    private float rate;
    private Date createDate;
    private Date updateDate;
}
