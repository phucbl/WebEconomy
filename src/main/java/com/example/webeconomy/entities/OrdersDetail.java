package com.example.webeconomy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "orderdetail")
public class OrdersDetail {
    @Id
    @Column(name ="order_id")
    private long orderId;
    
    // @Id
    @Column(name ="product_id")
    private long productId;

    @Column(name ="price")
    private float price;

    @Column(name ="quantity")
    private int quantity;

    public OrdersDetail(long orderId, long productId, float price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrdersDetail [orderId=" + orderId + ", productId=" + productId + ", price=" + price + ", quantity="
                + quantity + "]";
    }

    // @ManyToOne(mappedBy = "ordersDetail")
    // private Orders orders;


}
