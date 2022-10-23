package com.example.webeconomy.dto.request;
import com.example.webeconomy.data.entities.ProductCustomerId;
import lombok.Data;

@Data
public class RatingUpdateDto {
    private ProductCustomerId id;
    private int point;
}
