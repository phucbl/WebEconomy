package com.example.webeconomy.dto.response;
import com.example.webeconomy.data.entities.Product;
import com.example.webeconomy.data.entities.ProductCustomerId;
import lombok.Data;

@Data
public class CartResponseDto {
    private ProductCustomerId id;
    private int quantity;
    private boolean check;
    private Product product;
}
