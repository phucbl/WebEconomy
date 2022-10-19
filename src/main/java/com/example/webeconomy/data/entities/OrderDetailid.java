package com.example.webeconomy.data.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class OrderDetailid{
    @Column(name="order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;

}
