package com.example.webeconomy.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class OrderDetailid implements Serializable{
    @Column(name="order_id")
    private long orderId;

    @Column(name="product_id")
    private long productId;

    public OrderDetailid(){
        super();
    }

    public OrderDetailid(long orderId, long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

}
