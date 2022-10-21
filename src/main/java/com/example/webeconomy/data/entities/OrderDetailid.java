package com.example.webeconomy.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class OrderDetailId implements Serializable{
    @Column(name="order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;

    public OrderDetailId(){
        super();
    }

    public OrderDetailId(long orderId, long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderDetailid [orderId=" + orderId + ", productId=" + productId + "]";
    }

}
