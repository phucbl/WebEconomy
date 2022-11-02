package com.example.webeconomy.dto.request;
import java.util.List;
import com.example.webeconomy.data.entities.Cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDto {
    private List<Cart> carts;
}
